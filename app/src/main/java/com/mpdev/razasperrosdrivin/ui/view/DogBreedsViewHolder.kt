package com.mpdev.razasperrosdrivin.ui.view

import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mpdev.razasperrosdrivin.R
import com.mpdev.razasperrosdrivin.data.model.Breeds
import com.mpdev.razasperrosdrivin.databinding.DogviewBinding

class DogBreedsViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    private val binding = DogviewBinding.bind(view)
    // Obtener el ancho de la pantalla
    private val displayMetrics = view.context.resources.displayMetrics
    private val screenWidth = displayMetrics.widthPixels
    private val targetWidth = screenWidth / 2
    private lateinit var builder: AlertDialog

    // Función para renderizar la vista con los datos de una raza de perro
    fun render(breed: Breeds){

        val imgDog = binding.dog

        // Establecer el ancho del ImageView como la mitad del ancho de la pantalla
        imgDog.layoutParams.width = targetWidth
        imgDog.layoutParams.height = targetWidth

        // Actualizar el tamaño de la imagen
        imgDog.requestLayout()

        // Cargar la imagen de la URL usando Glide para mostrarla en el ImageView
        Glide.with(imgDog.context).load(breed.image).centerCrop().into(imgDog)

        // Manejar el clic en la imagen para mostrar un cuadro de diálogo con la raza del perro de la imagen
        imgDog.setOnClickListener {
            val dialog = AlertDialog.Builder(imgDog.context)
            val mView : View = LayoutInflater.from(imgDog.context).inflate(R.layout.display_breeds, null)
            dialog.setView(mView)
            builder = dialog.create()
            builder.show()

            val ok = mView.findViewById<Button>(R.id.button)
            val breedText = mView.findViewById<TextView>(R.id.textView)

            ok.setOnClickListener {
                builder.dismiss()
            }

            breedText.text = "La raza seleccionada es ${breed.breed}"

        }
    }
}