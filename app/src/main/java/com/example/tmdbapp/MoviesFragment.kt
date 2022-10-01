package com.example.tmdbapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.data.model.Movie
import com.example.tmdbapp.databinding.FragmentMoviesBinding
import com.example.tmdbapp.presentation.adapter.MovieAdapter
import com.example.tmdbapp.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.*

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter
    private var job: Job? = null

    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        //return inflater.inflate(R.layout.fragment_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        adapter = (activity as MainActivity).adapter

        initRecyclerView()
        getTopRatedMovies()
        setSearchView()
    }

    private fun getTopRatedMovies(){

        viewModel.getMovies(1)
        activity?.let {
            viewModel.movies.observe(it, Observer {
                if (it != null) {
                    adapter.differ.submitList(it)
                } else {
                    Toast.makeText(requireContext(), "No data available", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }

    private fun initRecyclerView(){
        adapter = adapter
        adapter.setOnItemClickListener {

            val bundle = Bundle().apply {
                putParcelable("selected_movie",it)
            }

            findNavController().navigate(
                R.id.action_moviesFragment_to_infoFragment,
                bundle
            )
        }
        binding.rvMovies.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvMovies.adapter = adapter
        binding.rvMovies.addOnScrollListener(onScrollListener)
    }

    private fun setSearchView(){

        binding.svMovies.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    //viewModel.getSearchedMovies(query!!)
                    viewSearchedNews(query!!)
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    job = MainScope().launch {
                        delay(2000)
                        if(query!!.length > 0 && job!!.isActive)
                            viewSearchedNews(query!!)
                    }
                    return false
                }

            })

        binding.svMovies.setOnCloseListener(
            object : SearchView.OnCloseListener{
                override fun onClose(): Boolean {
                    initRecyclerView()
                    getTopRatedMovies()
                    return false
                }

            })
    }

    fun viewSearchedNews(query: String){
        val responseLiveData = viewModel.getSearchedMovies(query)

        activity?.let {
            responseLiveData.observe(it, Observer {
                if (it != null) {
                    adapter.differ.submitList(it)
                } else {
                    Toast.makeText(requireContext(), "No data available", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }

    override fun onResume() {
        super.onResume()
        if(binding.svMovies.query.isNotEmpty())
            viewSearchedNews(binding.svMovies.query.toString())
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }

        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvMovies.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition+visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = hasReachedToEnd && isScrolling
            if(shouldPaginate && sizeOfTheCurrentList == 20){
                page++
                viewModel.getMovies(page)
                isScrolling = false

            }


        }
    }


}