package com.example.recetasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recetasapp.ui.adapter.RecetaAdapter
import com.example.recetasapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}