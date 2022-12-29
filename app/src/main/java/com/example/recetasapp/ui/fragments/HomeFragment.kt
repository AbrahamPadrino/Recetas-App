package com.example.recetasapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recetasapp.R
import com.example.recetasapp.databinding.FragmentHomeBinding
import com.example.recetasapp.ui.adapter.RecetaAdapter
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val recetasViewModel: RecetasViewModel by viewModels()

    private val recetaAdapter: RecetaAdapter by lazy { RecetaAdapter() }

    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recetasViewModel.liveData.observe(viewLifecycleOwner) {
            recetaAdapter.agregarRecetas(it)

            if (it.isEmpty()) {
                binding.outlinedTextField.visibility = View.GONE
                binding.tvTextoVacio.visibility = View.VISIBLE
                binding.gridRecyclerView.visibility = View.GONE
            } else {
                binding.outlinedTextField.visibility = View.VISIBLE
                binding.tvTextoVacio.visibility = View.GONE
                binding.gridRecyclerView.visibility = View.VISIBLE
            }
        }

        recetasViewModel.obtenerRecetas()

        chooseLayout()

        binding.outlinedTextField.editText?.doOnTextChanged { texto, _, _, _ ->
            recetaAdapter.filtrarRecetas(texto.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
    }

    private fun chooseLayout() {
        when (isLinearLayoutManager) {
            true -> {
                binding.gridRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.gridRecyclerView.adapter = recetaAdapter
            }
            false -> {
                binding.gridRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.gridRecyclerView.adapter = recetaAdapter
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Establece isLinearLayoutManager (un valor booleano) en el valor opuesto
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                chooseLayout()
                setIcon(item)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}