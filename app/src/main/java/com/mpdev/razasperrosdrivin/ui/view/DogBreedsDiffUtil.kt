package com.mpdev.razasperrosdrivin.ui.view

import androidx.recyclerview.widget.DiffUtil
import com.mpdev.razasperrosdrivin.data.model.Breeds

// Clase que implementa la interfaz DiffUtil.Callback para calcular las diferencias entre dos listas de razas de perros
class DogBreedsDiffUtil(private val oldList: List<Breeds>, private val newList: List<Breeds>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    // Comprueba si los elementos en la misma posición de ambas listas son los mismos
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].breed == newList[newItemPosition].breed
    }

    // Comprueba si los contenidos de los elementos en la misma posición de ambas listas son iguales
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}