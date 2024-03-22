package com.mpdev.razasperrosdrivin.data

import com.mpdev.razasperrosdrivin.data.model.BreedsModelResponse
import com.mpdev.razasperrosdrivin.data.model.ImageModelResponse
import com.mpdev.razasperrosdrivin.data.network.breeds.BreedsService
import com.mpdev.razasperrosdrivin.data.network.img_breeds.ImageBreedService
import javax.inject.Inject

class DogBreedsRepository @Inject constructor(
    private val apiBreeds: BreedsService,
    private val apiImageBreed: ImageBreedService
) {

    suspend fun getAllBreeds(): BreedsModelResponse {
        return apiBreeds.getAllBreeds()
    }

    suspend fun getImageBreed(breed: String): ImageModelResponse{
        return apiImageBreed.getRandomImageForBreed(breed)
    }
}