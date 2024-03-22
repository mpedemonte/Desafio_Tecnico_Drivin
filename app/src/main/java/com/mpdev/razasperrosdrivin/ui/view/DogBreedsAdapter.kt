package com.mpdev.razasperrosdrivin.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mpdev.razasperrosdrivin.R
import com.mpdev.razasperrosdrivin.data.model.Breeds


// Adaptador para la lista de razas de perros
class DogBreedsAdapter(private var breedsList: List<Breeds>): RecyclerView.Adapter<DogBreedsViewHolder>() {

    // Crea y devuelve un nuevo ViewHolder para el elemento de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogBreedsViewHolder(layoutInflater.inflate(R.layout.dogview, parent, false))
    }
    // Devuelve la cantidad de elementos en la lista
    override fun getItemCount(): Int {
        return breedsList.size
    }

    // Vincula los datos de la raza de perro en la posición especificada con el ViewHolder
    override fun onBindViewHolder(holder: DogBreedsViewHolder, position: Int) {
        val item = breedsList[position]
        holder.render(item)
    }

    // Actualiza los datos del adaptador con una nueva lista de razas de perros
    fun updateData(newData: List<Breeds>) {
        val dogBreedsDiff = DogBreedsDiffUtil(breedsList, newData)
        val result = DiffUtil.calculateDiff(dogBreedsDiff)
        breedsList = newData
        result.dispatchUpdatesTo(this)
    }

}