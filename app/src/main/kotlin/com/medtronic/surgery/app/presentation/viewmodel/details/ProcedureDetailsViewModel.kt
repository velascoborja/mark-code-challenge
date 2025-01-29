package com.medtronic.surgery.app.presentation.viewmodel.details

import androidx.lifecycle.ViewModel
import com.medtronic.surgery.app.data.repository.procedure.ProcedureRepository
import javax.inject.Inject

class ProcedureDetailsViewModel @Inject constructor(
    private val procedureRepository: ProcedureRepository
) : ViewModel() {

}