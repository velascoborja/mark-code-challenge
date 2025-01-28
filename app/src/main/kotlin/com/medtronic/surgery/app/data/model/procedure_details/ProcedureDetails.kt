package com.medtronic.surgery.app.data.model.procedure_details

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.medtronic.surgery.app.data.model.image.ImageMetadata

@Entity(tableName = "procedure_details")
data class ProcedureDetails(
    @PrimaryKey
    @SerializedName("uuid") val uuid: String,
    @SerializedName("phases") val phases: List<ProcedureDetailsPhase>? = null,
    @SerializedName("icon") val icon: ImageMetadata? = null,
    @SerializedName("card") val card: ImageMetadata? = null,
    @SerializedName("duration") val duration: Long,
    val isFavorite: Boolean = false, // This is not coming from the API but we need it for the UI
    @SerializedName("date_published") val publishedAt: String
)