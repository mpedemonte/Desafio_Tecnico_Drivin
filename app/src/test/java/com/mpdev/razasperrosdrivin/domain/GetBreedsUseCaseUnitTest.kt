package com.mpdev.razasperrosdrivin.domain

import com.mpdev.razasperrosdrivin.data.DogBreedsRepository
import com.mpdev.razasperrosdrivin.data.model.BreedsModelResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetBreedsUseCaseUnitTest {

    private lateinit var breedsUseCase: GetBreedsUseCase
    private val repository: DogBreedsRepository = mockk()

    @Before
    fun onBefore() {
        breedsUseCase = GetBreedsUseCase(repository)
    }

    //Verifica que el caso de uso [GetBreedsUseCase] devuelva el modelo de respuesta de razas esperado
    //cuando el repositorio proporciona la lista de razas.

    @Test
    fun `when the api returns list of breeds `() = runBlocking {
        // Given
        val expectedBreedsModelResponse: BreedsModelResponse = mockk()
        coEvery { repository.getAllBreeds() } returns expectedBreedsModelResponse

        // When
        val result = breedsUseCase()

        // Then
        assertEquals(expectedBreedsModelResponse, result)
    }
}