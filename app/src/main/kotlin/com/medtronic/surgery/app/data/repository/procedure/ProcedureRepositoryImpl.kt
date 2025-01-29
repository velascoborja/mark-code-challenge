package com.medtronic.surgery.app.data.repository.procedure

import com.medtronic.surgery.app.data.local.dao.ProcedureDao
import com.medtronic.surgery.app.data.local.dao.ProcedureDetailsDao
import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetails
import com.medtronic.surgery.app.data.service.procedure.ProcedureService
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProcedureRepositoryImpl @Inject constructor(
    private val procedureDao: ProcedureDao,
    private val procedureDetailsDao: ProcedureDetailsDao,
    private val procedureService: ProcedureService
) : ProcedureRepository {

    override suspend fun fetchProcedures(): List<Procedure> {
        return try {
            val procedures = procedureService.getProcedures()
            procedureDao.insertProcedures(procedures)
            procedures
        } catch (e: Exception) {
            procedureDao.getAllProcedures().firstOrNull() ?: emptyList()
        }
    }

    override suspend fun fetchProcedureDetails(uuid: String): ProcedureDetails {
        return try {
            val details = procedureService.getProcedureDetails(uuid)
            procedureDetailsDao.insertProcedureDetails(details)
            details
        } catch (e: Exception) {
            procedureDetailsDao.getProcedureDetailsByUuid(uuid).firstOrNull()
                ?: throw e
        }
    }

    override suspend fun toggleFavoriteStatus(uuid: String) {
        procedureDao
            .getProcedureByUuid(uuid)
            ?.let {
                val newFavoriteStatus = !it.isFavorite
                procedureDao.updateFavoriteStatus(uuid, newFavoriteStatus)
                // Update in the procedure details table if it exists
                procedureDetailsDao
                    .getProcedureDetailsByUuid(uuid)
                    .firstOrNull()
                    ?.let {
                        procedureDetailsDao.updateFavoriteStatus(uuid, newFavoriteStatus)
                    }
            }
    }
}