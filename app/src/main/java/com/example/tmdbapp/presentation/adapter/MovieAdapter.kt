package com.example.tmdbapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbapp.data.model.Movie
import com.example.tmdbapp.databinding.MovieItemBinding

class MovieAdapter(): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    private val movies = ArrayList<Movie>()

    fun setList(moviesList:List<Movie>){
        movies.clear()
        movies.addAll(moviesList)
    }

    inner class MovieViewHolder(binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root){
        val title = binding.titleTextView
        val rate = binding.ratingTextView
        val description = binding.descriptionTextView
        val image = binding.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        //val movie = movies[position]
        val movie = differ.currentList[position]

        holder.title.text = movie.title
        holder.rate.text = "${movie.voteAverage} / 10"
        holder.description.text = movie.overview

        val posterURL = "https://image.tmdb.org/t/p/w500"+movie.posterPath

        Glide.with(holder.image.context).
            load(posterURL).
            into(holder.image)

        holder.image.setOnClickListener {
            onItemClickListener?.let {
                it(movie)
            }
        }

        holder.image.setOnLongClickListener {
            onItemLongClickListener?.let {
                it(movie)
            }
            true
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener :((Movie)->Unit)?=null
    private var onItemLongClickListener :((Movie)->Unit)?=null

    fun setOnItemClickListener(listener : (Movie)->Unit){
        onItemClickListener = listener
    }

    fun setOnItemLongClickListener(listener : (Movie)->Unit){
        onItemLongClickListener = listener
    }
}