package com.medtronic.surgery.app.data.repository.procedure

import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetails
import com.medtronic.surgery.app.utils.network.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface ProcedureRepository {
    suspend fun refreshProcedures(): NetworkResponse<Unit>
    suspend fun watchProcedures(): Flow<List<Procedure>>
    suspend fun refreshProcedureDetails(uuid: String): NetworkResponse<Unit>
    suspend fun watchProcedureDetails(uuid: String): Flow<ProcedureDetails?>
    suspend fun toggleFavoriteStatus(uuid: String)
}