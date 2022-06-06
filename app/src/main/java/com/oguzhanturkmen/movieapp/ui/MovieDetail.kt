package com.oguzhanturkmen.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.oguzhanturkmen.movieapp.MainViewModell
import com.oguzhanturkmen.movieapp.POSTER_PATH
import com.oguzhanturkmen.movieapp.R
import com.oguzhanturkmen.movieapp.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetail : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    val args: MovieDetailArgs by navArgs()
    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModell::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater,container,false)
        val view = binding.root
        val movieArgs = args.movie
        Glide.with(this).load(POSTER_PATH+movieArgs.poster_path).into(binding.movideDetailImage)
        binding.movieDetailPoint.text = movieArgs.vote_average.toString()
        binding.movieDetailOverview.text= movieArgs.overview
        binding.movieDetailPublishDate.text = movieArgs.release_date
        binding.movieDetailTitle.text = movieArgs.title
        binding.fab.setOnClickListener {
            viewModel.saveMovie(movieArgs)
            Snackbar.make(view,"Movie Saved", Snackbar.LENGTH_SHORT).show()
        }
        return view
    }

}