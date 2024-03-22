package com.mpdev.razasperrosdrivin.domain

import com.mpdev.razasperrosdrivin.data.DogBreedsRepository
import com.mpdev.razasperrosdrivin.data.model.ImageModelResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetImageUseCaseUnitTest {

    private lateinit var imageUseCase: GetImageUseCase
    private val repository: DogBreedsRepository = mockk()

    @Before
    fun onBefore(){
        imageUseCase = GetImageUseCase(repository)
    }


     //Verifica que el caso de uso [GetImageUseCase] devuelva el modelo de respuesta de imagen esperado
     //cuando el repositorio proporciona la imagen de la raza.


    @Test
    fun `when the api return image of breed`() = runBlocking {
        //Given
        val breed = "akita"
        val expectedImageModelResponse: ImageModelResponse = mockk()
        coEvery {
            repository.getImageBreed(breed)
        }returns expectedImageModelResponse
        //When
        val response = imageUseCase(breed)


        //Then
        assertEquals(expectedImageModelResponse, response)
    }
}