package com.medtronic.surgery.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.medtronic.surgery.app.data.model.procedure.Procedure
import kotlinx.coroutines.flow.Flow

@Dao
interface ProcedureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProcedures(procedures: List<Procedure>)

    @Query("SELECT * FROM procedures")
    fun getAllProcedures(): Flow<List<Procedure>>

    @Query("SELECT * FROM procedures WHERE uuid = :uuid")
    suspend fun getProcedureByUuid(uuid: String): Procedure?

    @Query("SELECT * FROM procedures WHERE isFavorite = 1")
    fun getFavoriteProcedures(): Flow<List<Procedure>>

    @Query("UPDATE procedures SET isFavorite = :isFavorite WHERE uuid = :uuid")
    suspend fun updateFavoriteStatus(uuid: String, isFavorite: Boolean)
}