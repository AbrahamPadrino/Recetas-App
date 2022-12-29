package com.example.recetasapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recetasapp.databinding.FragmentDetailsBinding
import com.example.recetasapp.ui.adapter.IngredientesAdapter

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    private val args : DetailsFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recetaTitulo.text = args.receta.nombre
        binding.recetaIngredientes.apply {
            setHasFixedSize(true)
            adapter = IngredientesAdapter(args.receta.ingredientes)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        binding.recetaDescripcion.text = args.receta.descripcion
        Glide
            .with(requireContext())
            .load(args.receta.imagen)
            .into(binding.recetaImagen)

        binding.mapButton.setOnClickListener {
            findNavController().navigate(
                DetailsFragmentDirections.actionDetailsFragmentToMapsFragment2(args.receta)
            )
        }

    }


}