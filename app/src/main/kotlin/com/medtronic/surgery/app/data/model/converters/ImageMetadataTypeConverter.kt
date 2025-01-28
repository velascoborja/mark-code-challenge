package com.medtronic.surgery.app.data.model.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.medtronic.surgery.app.data.model.image.ImageMetadata

object ImageMetadataTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun toImageMetadata(
        data: String?
    ): ImageMetadata? {
        if (data == null) {
            return null
        }
        val type = object : TypeToken<ImageMetadata?>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromImageMetadata(
        data: ImageMetadata?
    ): String? {
        return gson.toJson(data)
    }
}