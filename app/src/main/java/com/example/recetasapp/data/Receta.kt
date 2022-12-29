package com.example.recetasapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Receta(
    val imagen: String,
    val nombre: String,
    val ingredientes: List<String>,
    val descripcion: String,
    val localizacionLat: Double,
    val localizacionLng: Double
) : Parcelable