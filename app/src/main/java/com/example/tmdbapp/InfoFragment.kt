package com.example.tmdbapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tmdbapp.data.model.Movie
import com.example.tmdbapp.databinding.FragmentInfoBinding
import com.example.tmdbapp.presentation.viewmodel.MovieViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar


class InfoFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: FragmentInfoBinding

    private var mMovie: Movie? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //(activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupMenu()
        viewModel = (activity as MainActivity).viewModel


        val args : InfoFragmentArgs by navArgs()
        val movie = args.selectedMovie

        if(movie != null){
            mMovie = movie
            loadMovieDetails(mMovie!!)
        }

    }

    private fun loadMovieDetails(movie: Movie){

        binding.tvTitle.text = movie.title
        binding.tvDescription.text = movie.overview
        binding.tvRating.text = "${movie.voteAverage} / 10"

        val posterURL = "https://image.tmdb.org/t/p/w500"+movie.posterPath

        Glide.with(requireContext()).
            load(posterURL).
            into(binding.imageView)
    }

    private fun setupMenu(){
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.info_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId){
//                    android.R.id.home -> {
//                        activity!!.onBackPressed()
//                    }
                    R.id.addMovie ->{
                        viewModel.saveMovie(mMovie!!)
                        view?.let { Snackbar.make(it,"Dodano do obejrzenia", Snackbar.LENGTH_SHORT).show() }
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }






}