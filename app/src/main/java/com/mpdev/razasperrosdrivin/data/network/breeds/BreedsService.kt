package com.mpdev.razasperrosdrivin.data.network.breeds

import com.mpdev.razasperrosdrivin.data.model.BreedsModelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BreedsService @Inject constructor(private val api: BreedsApiClient) {
    // Método para obtener todas las razas de perros desde la API
    suspend fun getAllBreeds() : BreedsModelResponse{
        return try {
            // Realiza la solicitud a la API en el contexto del hilo de fondo
            withContext(Dispatchers.IO){
                val response = api.getAllBreeds()
                response
            }
        } catch (e: Exception){
            // Maneja cualquier excepción que pueda ocurrir durante la solicitud y devuelve un objeto de respuesta de error
            BreedsModelResponse(status = "Error al obtener las razas de perro")
        }

    }



}