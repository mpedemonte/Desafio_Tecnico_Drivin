package com.mpdev.razasperrosdrivin.ui.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpdev.razasperrosdrivin.data.model.Breeds
import com.mpdev.razasperrosdrivin.data.model.ImageModelResponse
import com.mpdev.razasperrosdrivin.domain.GetBreedsUseCase
import com.mpdev.razasperrosdrivin.domain.GetImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.min

@HiltViewModel
class DogBreedsViewModel @Inject constructor(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val getImageUseCase: GetImageUseCase
) : ViewModel() {

    // Lista mutable para almacenar las razas de perros con imágenes
    private val breedList = mutableListOf<Breeds>()

    // Contador para controlar el número de elementos cargados
    private var loadedItemCount = 0

    // Tamaño de la muestra para cargar por lotes
    private val sampleSize = 10

    // LiveData para las razas de perros obtenidas de la API
    private val _listBreedsApi = MutableLiveData<List<String>?>()
    val listBreedsApi: MutableLiveData<List<String>?> = _listBreedsApi

    // LiveData para la lista de razas de perros con imágenes
    private val _listImage = MutableLiveData<List<Breeds>>()
    val listImage: LiveData<List<Breeds>> = _listImage

    // LiveData para indicar si se está cargando mas datos
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // LiveData para la visibilidad del progressBar
    private val _progressVisibility = MutableLiveData<Int>().apply { value = View.GONE }
    val progressVisibility: LiveData<Int> = _progressVisibility

    // LiveData para mensajes de error
    private val _errorToastMessage = MutableLiveData<String?>()
    val errorToastMessage: MutableLiveData<String?> = _errorToastMessage

    // Función para obtener las razas de perros de la API
    fun getBreeds() {
        viewModelScope.launch {
            val result = getBreedsUseCase()
            if (result.breeds != null) {
                _listBreedsApi.value = result.breeds
            } else {
                _errorToastMessage.value = result.status
            }
        }
    }

    // Función para mostrar las razas de perros con imágenes
    fun showBreeds(list: List<String>) {
        viewModelScope.launch {
            _progressVisibility.value = View.VISIBLE
            _isLoading.value = true

            // Calcular los índices de inicio y fin para cargar por lotes
            val startIndex = loadedItemCount
            val endIndex = min(startIndex + sampleSize, list.size)

            // Iterar sobre la lista de razas de perros
            for (i: Int in startIndex until endIndex) {
                val data: ImageModelResponse = getImageUseCase(list[i])
                if (data.image != null) {
                    // Si hay una imagen disponible, agregar a la lista de razas de perros con imágenes
                    breedList.add(Breeds(image = data.image, breed = list[i]))
                } else {
                    // Si la imagen no está disponible, agregar una imagen de error
                    breedList.add(
                        Breeds(
                            image = "https://img.freepik.com/vector-gratis/plantilla-web-error-404-lindo-perro_23-2147763341.jpg",
                            breed = "error"
                        )
                    )
                }

            }
            // Actualizar la lista de imágenes
            _listImage.value = breedList
            //Actualizar indice para mostrar lotes
            loadedItemCount = endIndex


            // Ocultar el progressBar
            _isLoading.value = false
            _progressVisibility.value = View.GONE

        }


    }

}


