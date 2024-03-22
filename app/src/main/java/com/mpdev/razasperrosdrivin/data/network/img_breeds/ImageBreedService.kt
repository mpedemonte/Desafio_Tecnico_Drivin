package com.mpdev.razasperrosdrivin.data.network.img_breeds

import com.mpdev.razasperrosdrivin.data.model.ImageModelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageBreedService @Inject constructor(private val api: ImageBreedApiClient) {

    suspend fun getRandomImageForBreed(breed: String) : ImageModelResponse {
        return try {
            withContext(Dispatchers.IO) {
                val response = api.getRandomImageForBreed(breed)
                response
            }
        } catch (e: Exception){
            ImageModelResponse(status = "Error al obtener la imagen de perro")
        }

    }



}