package com.medtronic.surgery.app.data.repository.procedure

import com.medtronic.surgery.app.data.local.dao.ProcedureDao
import com.medtronic.surgery.app.data.local.dao.ProcedureDetailsDao
import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetails
import com.medtronic.surgery.app.data.service.procedure.ProcedureService
import com.medtronic.surgery.app.utils.network.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProcedureRepositoryImpl @Inject constructor(
    private val procedureDao: ProcedureDao,
    private val procedureDetailsDao: ProcedureDetailsDao,
    private val procedureService: ProcedureService
) : ProcedureRepository {
    override suspend fun refreshProcedures(): NetworkResponse<Unit> {
        return try {
            val procedures = procedureService.getProcedures()
            procedureDao.insertProcedures(procedures)
            return NetworkResponse.Success(Unit)
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }
    }

    override suspend fun watchProcedures(): Flow<List<Procedure>> {
        return procedureDao.getAllProcedures()
    }

    override suspend fun refreshProcedureDetails(uuid: String): NetworkResponse<Unit> {
        return try {
            val details = procedureService.getProcedureDetails(uuid)
            procedureDetailsDao.insertProcedureDetails(details)
            return NetworkResponse.Success(Unit)
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }
    }

    override suspend fun watchProcedureDetails(uuid: String): Flow<ProcedureDetails?> {
        return procedureDetailsDao.getProcedureDetailsByUuid(uuid)
    }

    override suspend fun toggleFavoriteStatus(uuid: String) {
        // Update favorite status in the procedure list table
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