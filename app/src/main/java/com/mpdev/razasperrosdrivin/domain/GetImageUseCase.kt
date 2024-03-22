package com.mpdev.razasperrosdrivin.domain

import com.mpdev.razasperrosdrivin.data.DogBreedsRepository
import com.mpdev.razasperrosdrivin.data.model.ImageModelResponse
import javax.inject.Inject

// Caso de uso para obtener la imagen de una raza de perro específica
class GetImageUseCase @Inject constructor(private val repository: DogBreedsRepository){
    suspend operator fun invoke(breed:String) : ImageModelResponse{
        return repository.getImageBreed(breed)
    }
}