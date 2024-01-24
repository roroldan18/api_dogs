package com.example.doglist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    private lateinit var imagesAdapter: ImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val apiService = (application as MyApplication).apiService
        val selectedBreedId = intent.getStringExtra("breedId") ?: ""

        if (selectedBreedId.isBlank()) {
            finish()
            return
        }

        // Configura el RecyclerView y su Adapter
        imagesAdapter = ImagesAdapter()
        val photoRecyclerView = findViewById<RecyclerView>(R.id.photoRecyclerView)
        photoRecyclerView.layoutManager = GridLayoutManager(this, 2) // Puedes ajustar el número de columnas
        photoRecyclerView.adapter = imagesAdapter

        GlobalScope.launch(Dispatchers.Main) {
            val apiKey = "live_k4yCRg9AhcmKsWtEgDR37qSn1XiwZdVNimOcuGuGgk5I2B5DD0AcHRYI6XH6VshR"
            val images = apiService.getImages(apiKey, selectedBreedId, 5)
            // Obtener la lista de URL de las imágenes
            val imageUrls = images.map { it.url }

            // Actualiza el Adapter con la lista de URL de imágenes
            imagesAdapter.submitList(imageUrls)
        }
    }
}
