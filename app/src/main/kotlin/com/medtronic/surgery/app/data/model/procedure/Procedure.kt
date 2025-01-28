package com.medtronic.surgery.app.data.model.procedure

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.medtronic.surgery.app.data.model.image.ImageMetadata

@Entity(tableName = "procedures")
data class Procedure(
    @PrimaryKey
    @SerializedName("uuid") val uuid: String,
    @SerializedName("name") val name: String,
    @SerializedName("phases") val phases: List<String>? = null,
    @SerializedName("icon") val thumbnail: ImageMetadata? = null,
    @SerializedName("duration") val duration: Long,
    val isFavorite: Boolean = false, // This is not coming from the API but we need it for the UI
    @SerializedName("date_published") val publishedAt: String
)