package com.mpdev.razasperrosdrivin.data.network.breeds

import com.mpdev.razasperrosdrivin.data.model.BreedsModelResponse
import retrofit2.http.GET

interface BreedsApiClient {
    // Método para obtener todas las razas de perros desde la API
    @GET("breeds/list")
    suspend fun getAllBreeds(): BreedsModelResponse
}