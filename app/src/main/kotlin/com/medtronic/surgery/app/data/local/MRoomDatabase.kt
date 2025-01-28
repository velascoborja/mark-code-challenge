package com.medtronic.surgery.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.medtronic.surgery.app.data.local.dao.ProcedureDao
import com.medtronic.surgery.app.data.local.dao.ProcedureDetailsDao
import com.medtronic.surgery.app.data.model.converters.ImageMetadataTypeConverter
import com.medtronic.surgery.app.data.model.converters.ProcedurePhaseTypeConverter
import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetails

@Database(
    entities = [
        Procedure::class,
        ProcedureDetails::class
    ],
    version = 3,
    exportSchema = false
)
@TypeConverters(
    ImageMetadataTypeConverter::class,
    ProcedurePhaseTypeConverter::class,
)
abstract class MRoomDatabase : RoomDatabase() {
    abstract fun procedureDao(): ProcedureDao
    abstract fun procedureDetailsDao(): ProcedureDetailsDao
}