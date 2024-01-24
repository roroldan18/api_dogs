package com.example.doglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import controlador.BreedAdapter
import controlador.OnBreedClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.Breed

class MainActivity : AppCompatActivity(), OnBreedClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var breedAdapter: BreedAdapter

    override fun onBreedClick(breed: Breed) {
        // Aquí manejas la selección de la raza
        // Puedes iniciar la nueva actividad y pasar el ID de la raza, por ejemplo.
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("breedId", breed.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewBreeds)
        breedAdapter = BreedAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = breedAdapter

        val apiService = (application as MyApplication).apiService

        GlobalScope.launch(Dispatchers.Main) {
            val apiKey = "live_k4yCRg9AhcmKsWtEgDR37qSn1XiwZdVNimOcuGuGgk5I2B5DD0AcHRYI6XH6VshR"
            val breeds = apiService.getBreeds(apiKey)
            breedAdapter.setBreeds(breeds)
        }

    }
}