package com.oguzhanturkmen.movieapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.oguzhanturkmen.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        bottomNavigationView.setupWithNavController(moviesNavHostFragment.findNavController())
        moviesNavHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                if (
                    destination.id == R.id.homeFragment ||
                    destination.id == R.id.savedMovieFragment ||
                    destination.id == R.id.searchMovieFragment

                ) {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                } else {
                    binding.bottomNavigationView.visibility = View.GONE
                }


            }

    }
}