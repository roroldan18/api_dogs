package service

import modelo.Breed
import modelo.Image
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //https://api.thecatapi.com/v1/images/search?api_key=live_k4yCRg9AhcmKsWtEgDR37qSn1XiwZdVNimOcuGuGgk5I2B5DD0AcHRYI6XH6VshR

    @GET("breeds")
    suspend fun getBreeds(@Query("api_key") apiKey: String): List<Breed>

    @GET("images/search")
    suspend fun getImages(@Query("api_key") apiKey: String, @Query("breed_ids") breedId: String, @Query("limit") limit: Int): List<Image>
}