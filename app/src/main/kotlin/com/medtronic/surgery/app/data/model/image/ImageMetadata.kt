package com.medtronic.surgery.app.data.model.image

import com.google.gson.annotations.SerializedName

data class ImageMetadata(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("url") val url: String,
    @SerializedName("version") val version: String
)