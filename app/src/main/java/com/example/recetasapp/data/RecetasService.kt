package com.example.recetasapp.data

import retrofit2.Response
import retrofit2.http.GET

interface RecetasService {
    @GET("recetas")
    suspend fun getRecetas(): Response<List<Receta>>
}