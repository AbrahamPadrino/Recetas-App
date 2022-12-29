package com.example.recetasapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recetasapp.R
import com.example.recetasapp.data.Receta
import com.example.recetasapp.ui.fragments.HomeFragmentDirections
import com.example.recetasapp.ui.fragments.HomeFragmentDirections.ActionHomeFragmentToDetailsFragment
import java.util.*

class RecetaAdapter : RecyclerView.Adapter<RecetaAdapter.RecetaCardViewHolder>() {

    private val dataset = mutableListOf<Receta>()
    private val listOriginal = mutableListOf<Receta>()
    private var resultadoFiltro: List<Receta> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaCardViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item,parent,false)
        return RecetaCardViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RecetaCardViewHolder, position: Int) {
        val item = dataset[position]
        // holder.imageView.setImageResource(item.imagen)
        holder.textName.text = item.nombre
        Glide
            .with(holder.itemView.context)
            .load(item.imagen)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item)
            it.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int = dataset.size

    fun agregarRecetas(items: List<Receta>) {
        dataset.clear()
        dataset.addAll(items)
        if (listOriginal.isEmpty()) listOriginal.addAll(dataset)
        notifyDataSetChanged()
    }

    fun filtrarRecetas(filtrar: String) {
        if (filtrar.isNotBlank()) {
            resultadoFiltro = listOriginal.filter {
                it.nombre.contains(filtrar, true) or it.ingredientes.any { ingrediente -> ingrediente.contains(filtrar, true) }
            }.toList()

            agregarRecetas(resultadoFiltro)
        } else agregarRecetas(listOriginal)
    }

    inner class RecetaCardViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        val imageView: ImageView = itemView!!.findViewById(R.id.imageView)
        val textName: TextView = itemView!!.findViewById(R.id.textName)
    }
}