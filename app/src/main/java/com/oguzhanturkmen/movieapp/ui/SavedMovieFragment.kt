package com.oguzhanturkmen.movieapp.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.oguzhanturkmen.movieapp.MainViewModell
import com.oguzhanturkmen.movieapp.R
import com.oguzhanturkmen.movieapp.databinding.FragmentSavedMovieBinding
import com.oguzhanturkmen.movieapp.rvAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedMovieFragment : Fragment() {
    private var _binding: FragmentSavedMovieBinding? = null
    private val binding get() = _binding!!
    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModell::class.java)
    }
    private lateinit var rvAdapter: rvAdapter
    override fun onResume() {
        super.onResume()
        viewModel.getSavedMovies()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAdapter = rvAdapter()
        binding.rvSavedMovies.adapter = rvAdapter
        viewModel.savedlivedata.observe(viewLifecycleOwner, Observer {
            rvAdapter.differ.submitList(it)
        })


        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val movie = rvAdapter.differ.currentList[position]
                viewModel.deleteMovies(movie)
                Snackbar.make(view, "DELETED", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo") {
                        viewModel.saveMovie(movie)
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvSavedMovies)
        }
    }
}