package com.example.tmdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbapp.data.model.Movie
import com.example.tmdbapp.databinding.ActivityMainBinding
import com.example.tmdbapp.presentation.adapter.MovieAdapter
import com.example.tmdbapp.presentation.viewmodel.MovieViewModel
import com.example.tmdbapp.presentation.viewmodel.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MovieViewModelFactory
    @Inject
    lateinit var adapter: MovieAdapter
    lateinit var viewModel: MovieViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bnvMovies.setupWithNavController(
            navController
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.infoFragment) {

                binding.bnvMovies.visibility = View.GONE
            } else {
                binding.bnvMovies.visibility = View.VISIBLE
            }
        }

        viewModel = ViewModelProvider(this,factory)
            .get(MovieViewModel::class.java)

    }


}