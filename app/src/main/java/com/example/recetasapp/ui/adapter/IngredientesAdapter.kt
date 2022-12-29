package com.example.recetasapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recetasapp.databinding.ListItemIngredienteBinding

class IngredientesAdapter(
    private val ingredientes: List<String>
) : RecyclerView.Adapter<IngredientesAdapter.IngredienteViewHolder>() {



    inner class IngredienteViewHolder(private val binding: ListItemIngredienteBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(ingrediente: String) {
                val ingredienteArreglado = "- $ingrediente"
                binding.ingredienteItem.text = ingredienteArreglado
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredienteViewHolder {
        return IngredienteViewHolder(
            ListItemIngredienteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IngredienteViewHolder, position: Int) {
        holder.bind(ingredientes[position])
    }

    override fun getItemCount(): Int {
        return ingredientes.size
    }
}
