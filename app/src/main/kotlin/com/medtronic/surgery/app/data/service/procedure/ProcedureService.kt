package com.medtronic.surgery.app.data.service.procedure

import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface ProcedureService {
    /**
     * Fetches the list of procedures.
     * @return List of [Procedure] objects.
     */
    @GET("api/v3/procedures")
    suspend fun getProcedures(): List<Procedure>

    /**
     * Fetches the details of a specific procedure.
     * @param procedureId The ID of the procedure to fetch details for.
     * @return A detailed [Procedure] object.
     */
    @GET("api/v3/procedures/{procedureId}")
    suspend fun getProcedureDetails(
        @Path("procedureId") procedureId: String
    ): ProcedureDetails
}