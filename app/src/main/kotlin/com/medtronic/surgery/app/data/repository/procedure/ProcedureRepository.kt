package com.medtronic.surgery.app.data.repository.procedure

import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetails

interface ProcedureRepository {
    /**
     * Fetches the list of procedures.
     */
    suspend fun fetchProcedures(): List<Procedure>

    /**
     * Fetches the details of a specific procedure.
     * @param uuid The UUID of the procedure to fetch details for.
     */
    suspend fun fetchProcedureDetails(uuid: String): ProcedureDetails

    /**
     * Toggles the favorite status of a procedure.
     * @param uuid The UUID of the procedure to toggle the favorite status for.
     */
    suspend fun toggleFavoriteStatus(uuid: String)
}