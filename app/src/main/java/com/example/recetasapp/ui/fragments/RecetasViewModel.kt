package com.example.recetasapp.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recetasapp.data.Receta
import com.example.recetasapp.data.RecetasRepository
import com.example.recetasapp.data.RecetasService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecetasViewModel @Inject constructor(
    private val recetasRepository: RecetasRepository
) : ViewModel() {

    val liveData: MutableLiveData<List<Receta>> = MutableLiveData<List<Receta>>()

    private val recetas = mutableListOf<Receta>()

    fun obtenerRecetas() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = recetasRepository.obtenerRecetas()
            if (recetas.isNotEmpty()) recetas.clear()
            recetas.addAll(result)
            liveData.postValue(result)
        }
    }

}