package com.medtronic.surgery.app.data.model.procedure_details

import com.google.gson.annotations.SerializedName
import com.medtronic.surgery.app.data.model.image.ImageMetadata

data class ProcedureDetailsPhase(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("name") val name: String,
    @SerializedName("icon") val icon: ImageMetadata,
)