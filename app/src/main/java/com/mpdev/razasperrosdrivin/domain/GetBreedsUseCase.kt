package com.mpdev.razasperrosdrivin.domain

import com.mpdev.razasperrosdrivin.data.DogBreedsRepository
import com.mpdev.razasperrosdrivin.data.model.BreedsModelResponse
import javax.inject.Inject

// Caso de uso para obtener todas las razas de perros
class GetBreedsUseCase @Inject constructor(private val repository: DogBreedsRepository) {
    suspend operator fun invoke(): BreedsModelResponse {
        return repository.getAllBreeds()
    }
}