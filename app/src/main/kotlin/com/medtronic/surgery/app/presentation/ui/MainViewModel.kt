package com.medtronic.surgery.app.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medtronic.surgery.app.data.repository.procedure.ProcedureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val procedureRepository: ProcedureRepository
) : ViewModel() {

    fun refreshProcedures() {
        viewModelScope.launch {
            procedureRepository.refreshProcedures()
        }
        viewModelScope.launch {
            procedureRepository.refreshProcedureDetails(uuid = "procedure-TSC_MedPara")
        }
        viewModelScope.launch {
            procedureRepository.toggleFavoriteStatus(uuid = "procedure-TSC_MedPara")
        }
    }
}