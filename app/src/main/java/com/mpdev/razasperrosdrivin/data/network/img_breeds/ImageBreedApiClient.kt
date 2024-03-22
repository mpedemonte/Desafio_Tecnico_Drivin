package com.mpdev.razasperrosdrivin.data.network.img_breeds

import com.mpdev.razasperrosdrivin.data.model.ImageModelResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageBreedApiClient {
    @GET("breed/{breed}/images/random")
    suspend fun getRandomImageForBreed(@Path("breed") breed: String): ImageModelResponse
}