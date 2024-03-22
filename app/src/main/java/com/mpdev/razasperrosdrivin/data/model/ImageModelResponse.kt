package com.mpdev.razasperrosdrivin.data.model

import com.google.gson.annotations.SerializedName

class ImageModelResponse (
    @SerializedName("message") val image: String? = null,
    @SerializedName("status") val status: String? = null
    )