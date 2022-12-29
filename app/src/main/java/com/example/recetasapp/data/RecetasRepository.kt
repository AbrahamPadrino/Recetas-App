package com.example.recetasapp.data

import javax.inject.Inject

class RecetasRepository
@Inject constructor(private val service: RecetasService) {

    suspend fun obtenerRecetas(): List<Receta> {
        try {
            val response = service.getRecetas()
            return if (response.isSuccessful) {
                 response.body()?: emptyList()
            } else emptyList()
        } catch (e: Exception) {
            throw  Error(e.message ?: e.toString())
        }
    }

}