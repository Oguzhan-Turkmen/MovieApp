package com.oguzhanturkmen.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.oguzhanturkmen.movieapp.MainViewModel
import com.oguzhanturkmen.movieapp.R
import com.oguzhanturkmen.movieapp.databinding.FragmentSearchMovieBinding
import com.oguzhanturkmen.movieapp.model.Movies
import com.oguzhanturkmen.movieapp.rvAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMovieFragment : Fragment() {
    private lateinit var binding: FragmentSearchMovieBinding
    val viewModell by lazy{
        ViewModelProvider(this,defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }
    private lateinit var rvAdapter: rvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchMovieBinding.inflate(inflater,container,false)
        rvAdapter = rvAdapter()
        binding.rvSearchMovies.adapter = rvAdapter
        rvAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie",it)
            }
            findNavController().navigate(
                R.id.action_searchMovieFragment_to_movieDetail,bundle

            )
        }
        binding.etSearch.addTextChangedListener { editable ->
            editable?.let{
                if (editable.toString().isNotEmpty()){
                    viewModell.loadSearchData("1",editable.toString())
                   viewModell.getObserverSearvhLiveData().observe(this,object : Observer<Movies>{
                       override fun onChanged(t: Movies?) {
                           if (t!=null){
                               rvAdapter.differ.submitList(t.results)
                           }
                       }

                   })
                }
            }
        }
        return binding.root
    }


}