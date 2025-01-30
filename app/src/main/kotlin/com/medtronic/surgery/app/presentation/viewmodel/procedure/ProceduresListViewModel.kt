package com.medtronic.surgery.app.presentation.viewmodel.procedure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medtronic.surgery.app.data.analytics.AnalyticsClient
import com.medtronic.surgery.app.data.analytics.AnalyticsEvent
import com.medtronic.surgery.app.data.analytics.AnalyticsScreenView
import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.data.repository.procedure.ProcedureRepository
import com.medtronic.surgery.app.presentation.ui.filter.FilterProcedureType
import com.medtronic.surgery.app.presentation.ui.procedures.ProceduresListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ProceduresListViewModel @Inject constructor(
    private val procedureRepository: ProcedureRepository,
    private val analytics: AnalyticsClient
) : ViewModel() {

    private val _proceduresListState =
        MutableStateFlow<ProceduresListState>(ProceduresListState.Loading)
    open val proceduresListState: StateFlow<ProceduresListState> get() = _proceduresListState

    // this will be used for the pull to refresh
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    init {
        fetchProcedures()
    }

    fun fetchProcedures() {
        viewModelScope.launch {
            try {
                val procedures = procedureRepository.fetchProcedures()
                updateProcedureState(procedures)
            } catch (e: Exception) {
                _proceduresListState.update {
                    ProceduresListState.Error(e.localizedMessage ?: "Unknown error")
                }
            }
        }
    }

    fun refreshProcedures() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val procedures = procedureRepository.fetchProcedures()
                updateProcedureState(procedures)
            } catch (e: Exception) {
                updateErrorState(e)
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun toggleFavorite(uuid: String) {
        val event = AnalyticsEvent(AnalyticsEvent.EVENT_TOGGLE_FAVORITE)
        event.addParameter(AnalyticsEvent.Key.UUID, uuid)
        analytics.sendEvent(event)

        viewModelScope.launch {
            procedureRepository.toggleFavoriteStatus(uuid)
            // ideally, marking as favorite should not be cached locally instead it should be fetched from the server
            // but for the sake of this demo, we will update the local cache
            _proceduresListState.update { currentState ->
                if (currentState is ProceduresListState.Success) {
                    val updatedProcedures = currentState.procedures.map { procedure ->
                        if (procedure.uuid == uuid) {
                            procedure.copy(isFavorite = !procedure.isFavorite)
                        } else {
                            procedure
                        }
                    }
                    ProceduresListState.Success(updatedProcedures)
                } else {
                    currentState
                }
            }
        }
    }

    private fun updateProcedureState(procedures: List<Procedure>) {
        if (procedures.isNotEmpty()) {
            _proceduresListState.update {
                ProceduresListState.Success(procedures)
            }
        } else {
            _proceduresListState.update {
                ProceduresListState.Error("No procedures found")
            }
        }
    }

    private fun updateErrorState(e: Exception) {
        _proceduresListState.update {
            ProceduresListState.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    fun filterProcedures(
        procedures: List<Procedure>,
        searchQuery: String,
        selectedFilter: FilterProcedureType,
        filterFavorites: (Procedure) -> Boolean
    ): List<Procedure> {
        val filteredProcedures = procedures.filter { procedure ->
            filterFavorites(procedure) && procedure.name.contains(searchQuery, ignoreCase = true)
        }
        // filter is limited to one for now but can be extended to multiple filters
        return when (selectedFilter) {
            FilterProcedureType.DURATION_ASCENDING -> filteredProcedures.sortedBy { it.duration }
            FilterProcedureType.DURATION_DESCENDING -> filteredProcedures.sortedByDescending { it.duration }
            FilterProcedureType.ALPHABETICAL_ASCENDING -> filteredProcedures.sortedBy { it.name }
            FilterProcedureType.ALPHABETICAL_DESCENDING -> filteredProcedures.sortedByDescending { it.name }
            FilterProcedureType.NONE -> filteredProcedures
        }
    }

    fun sendAnalyticsScreenViewed(type: ProceduresListType) {
        analytics.sendScreenView(AnalyticsScreenView(type.screenName))
    }

    fun sendAnalyticsEventFilterClicked() {
        analytics.sendEvent(AnalyticsEvent(AnalyticsEvent.EVENT_FILTER_CLICKED))
    }

    fun sendAnalyticsEventFilterTypeSelected(filterType: FilterProcedureType) {
        val event = AnalyticsEvent(AnalyticsEvent.EVENT_FILTER_TYPE_SELECTED)
        event.addParameter(AnalyticsEvent.Key.NAME, filterType.name)
        analytics.sendEvent(event)
    }

    sealed interface ProceduresListState {
        data object Loading : ProceduresListState
        data class Error(val message: String) : ProceduresListState
        data class Success(val procedures: List<Procedure>) : ProceduresListState
    }
}