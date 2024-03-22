package com.mpdev.razasperrosdrivin.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mpdev.razasperrosdrivin.databinding.ActivityMainBinding
import com.mpdev.razasperrosdrivin.ui.viewmodel.DogBreedsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val dogBreedsViewModel: DogBreedsViewModel by viewModels()

    private val adapter: DogBreedsAdapter by lazy { DogBreedsAdapter(emptyList()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Razas de perros"

        setupRecyclerView()
        setupObservers()
        // Obtener la lista de razas de perros
        dogBreedsViewModel.getBreeds()
        // Mostrar las razas de perros
        showBreeds()


    }

    private fun setupObservers(){

        // Observar y mostrar un mensaje de error si ocurre algún problema al obtener las raza de los perros
        dogBreedsViewModel.errorToastMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        // Observar la lista de imágenes de razas de perros y actualizar el adaptador
        dogBreedsViewModel.listImage.observe(this) {
            adapter.updateData(it)
        }

        // Observar la visibilidad del progressBar y actualizar la visibilidad de este
        dogBreedsViewModel.progressVisibility.observe(this) {
            binding.progress.visibility = it
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.ListDogs
        // Configurar el RecyclerView con un GridLayoutManager y el adaptador
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        // Agregar un listener de scroll al RecyclerView para cargar más razas de perros cuando se llega al final
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()


                val totalItemCount = layoutManager.itemCount

                if (dogBreedsViewModel.isLoading.value == false && lastVisibleItemPosition == totalItemCount - 1) {
                    loadMoreBreeds()

                }
            }
        })
    }

    // Función para mostrar las razas de perros
    private fun showBreeds() {
        dogBreedsViewModel.listBreedsApi.observe(this) {
            if (it != null) {
                dogBreedsViewModel.showBreeds(it)
            }
        }
    }

    // Función para cargar más razas de perros
    private fun loadMoreBreeds() {
        showBreeds()
    }
}