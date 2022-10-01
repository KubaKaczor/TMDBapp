package com.example.tmdbapp.data.repository.dataSource

import com.example.tmdbapp.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {

    suspend fun saveMovieToDb(movie: Movie)
    fun getSavedMovies(): Flow<List<Movie>>
    suspend fun deleteMovie(movie: Movie)
}