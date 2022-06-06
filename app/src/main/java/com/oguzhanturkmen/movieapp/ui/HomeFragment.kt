package com.oguzhanturkmen.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.oguzhanturkmen.movieapp.MainViewModell
import com.oguzhanturkmen.movieapp.R
import com.oguzhanturkmen.movieapp.databinding.FragmentHomeBinding
import com.oguzhanturkmen.movieapp.model.Movies
import com.oguzhanturkmen.movieapp.rvAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    val viewModel by lazy{
        ViewModelProvider(this,defaultViewModelProviderFactory).get(MainViewModell::class.java)
    }
    private lateinit var rvAdapter: rvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        rvAdapter = rvAdapter()
        val recylerView = binding.rvHomeFragment
        recylerView.adapter = rvAdapter
        rvAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie",it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_movieDetail,bundle
            )
        }
        viewModel.getObserverLiveData().observe(this,object: Observer<Movies> {
            override fun onChanged(t: Movies?) {
                if ( t!= null){
                rvAdapter.differ.submitList(t.results)

                   // rvAdapter.setList(t.results)
                }
            }

        })
        viewModel.loadPopularData("1")
        return binding.root
    }
    }
