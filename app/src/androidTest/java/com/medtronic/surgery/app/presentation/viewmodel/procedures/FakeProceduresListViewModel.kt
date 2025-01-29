package com.medtronic.surgery.app.presentation.viewmodel.procedures

import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.presentation.viewmodel.procedure.ProceduresListViewModel
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeProceduresListViewModel(procedures: List<Procedure>) : ProceduresListViewModel(mockk()) {
    private val _proceduresListState =
        MutableStateFlow<ProceduresListState>(ProceduresListState.Success(procedures))
    override val proceduresListState: StateFlow<ProceduresListState> get() = _proceduresListState
}