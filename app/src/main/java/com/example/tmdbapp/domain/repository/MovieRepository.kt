package com.example.tmdbapp.domain.repository

import com.bumptech.glide.load.engine.Resource
import com.example.tmdbapp.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(page: Int): List<Movie>?
    suspend fun getSearchedMovies(query: String): List<Movie>?
    fun getSavedMovies(): Flow<List<Movie>>?
    suspend fun saveMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
}