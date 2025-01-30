package com.medtronic.surgery.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface ProcedureDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProcedureDetails(details: ProcedureDetails)

    @Query("SELECT * FROM procedure_details  WHERE uuid = :uuid LIMIT 1")
    fun getProcedureDetailsByUuid(uuid: String): Flow<ProcedureDetails?>

    @Query("UPDATE procedure_details SET isFavorite = :isFavorite WHERE uuid = :uuid")
    suspend fun updateFavoriteStatus(uuid: String, isFavorite: Boolean)
}