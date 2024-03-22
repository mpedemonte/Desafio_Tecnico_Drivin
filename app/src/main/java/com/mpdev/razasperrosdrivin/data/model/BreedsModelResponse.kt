package com.mpdev.razasperrosdrivin.data.model

import com.google.gson.annotations.SerializedName

data class BreedsModelResponse(
    @SerializedName("message") val breeds: List<String>? = null,
    @SerializedName("status") val status: String? = null,
)
