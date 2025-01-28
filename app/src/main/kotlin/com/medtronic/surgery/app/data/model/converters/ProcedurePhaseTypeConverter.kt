package com.medtronic.surgery.app.data.model.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetailsPhase

object ProcedurePhaseTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun toProcedurePhase(
        data: String?
    ): ProcedureDetailsPhase? {
        if (data == null) {
            return null
        }
        val type = object : TypeToken<ProcedureDetailsPhase?>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromProcedurePhase(
        data: ProcedureDetailsPhase?
    ): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toProcedurePhaseList(
        data: String?
    ): List<ProcedureDetailsPhase>? {
        if (data == null) {
            return null
        }
        val type = object : TypeToken<List<ProcedureDetailsPhase>?>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromProcedurePhaseList(
        data: List<ProcedureDetailsPhase>?
    ): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toProcedurePhaseStringList(
        data: String?
    ): List<String>? {
        if (data == null) {
            return null
        }
        val type = object : TypeToken<List<String>?>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromProcedurePhaseStringList(
        data: List<String>?
    ): String? {
        return gson.toJson(data)
    }
}