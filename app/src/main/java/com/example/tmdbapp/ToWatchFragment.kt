package com.example.tmdbapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbapp.databinding.FragmentToWatchBinding
import com.example.tmdbapp.presentation.adapter.MovieAdapter
import com.example.tmdbapp.presentation.viewmodel.MovieViewModel


class ToWatchFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter
    private lateinit var binding: FragmentToWatchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToWatchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        adapter = (activity as MainActivity).adapter

        initRecyclerView()
    }

    private fun loadSavedMovies(){

        val responseLiveData = viewModel.getSavedMovies()
        activity?.let {
            responseLiveData.observe(it, Observer {
                if(it != null) {
                    //adapter.setList(it)
                    adapter.differ.submitList(it)
                    if(it.size > 0){
                        binding.tvNoMovies.visibility = View.GONE
                        binding.rvMovies.visibility = View.VISIBLE
                    }else{
                        binding.tvNoMovies.visibility = View.VISIBLE
                        binding.rvMovies.visibility = View.GONE
                    }
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
                R.id.action_toWatchFragment_to_infoFragment,
                bundle
            )
        }

        adapter.setOnItemLongClickListener {
            viewModel.deleteMovie(it)
            Toast.makeText(requireContext(), "Pomyślnie usunięto", Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
        }

        binding.rvMovies.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvMovies.adapter = adapter

        loadSavedMovies()
    }
}