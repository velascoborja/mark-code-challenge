package com.medtronic.surgery.app.presentation.viewmodel.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetails
import com.medtronic.surgery.app.data.repository.procedure.ProcedureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcedureDetailsViewModel @Inject constructor(
    private val procedureRepository: ProcedureRepository
) : ViewModel() {
    private val _procedureDetailsState =
        MutableStateFlow<ProcedureDetailsState>(ProcedureDetailsState.Loading)
    val procedureDetailsState: StateFlow<ProcedureDetailsState> get() = _procedureDetailsState

    fun fetchProcedureDetails(uuid: String) {
        viewModelScope.launch {
            try {
                val details = procedureRepository.fetchProcedureDetails(uuid)
                _procedureDetailsState.emit(ProcedureDetailsState.Success(details))
            } catch (e: Exception) {
                _procedureDetailsState.emit(
                    ProcedureDetailsState.Error(
                        e.localizedMessage ?: "Unknown error"
                    )
                )
            }
        }
    }

    fun toggleFavorite(uuid: String) {
        viewModelScope.launch {
            procedureRepository.toggleFavoriteStatus(uuid)
            // ideally, marking as favorite should not be cached locally instead it should be fetched from the server
            // but for the sake of this demo, we will update the local cache
            _procedureDetailsState.update { currentState ->
                if (currentState is ProcedureDetailsState.Success) {
                    val updatedDetails = currentState.details.copy(isFavorite = !currentState.details.isFavorite)
                    ProcedureDetailsState.Success(updatedDetails)
                } else {
                    currentState
                }
            }
        }
    }

    sealed interface ProcedureDetailsState {
        data object Loading : ProcedureDetailsState
        data class Success(val details: ProcedureDetails) : ProcedureDetailsState
        data class Error(val message: String) : ProcedureDetailsState
    }
}