package com.medtronic.surgery.app.presentation.viewmodel.procedure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.data.repository.procedure.ProcedureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProceduresListViewModel @Inject constructor(
    private val procedureRepository: ProcedureRepository
) : ViewModel() {

    private val _proceduresListState =
        MutableStateFlow<ProceduresListState>(ProceduresListState.Loading)
    val proceduresListState: StateFlow<ProceduresListState> get() = _proceduresListState

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
        viewModelScope.launch {
            procedureRepository.toggleFavoriteStatus(uuid)
            fetchProcedures()
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

    sealed interface ProceduresListState {
        data object Loading : ProceduresListState
        data class Error(val message: String) : ProceduresListState
        data class Success(val procedures: List<Procedure>) : ProceduresListState
    }
}