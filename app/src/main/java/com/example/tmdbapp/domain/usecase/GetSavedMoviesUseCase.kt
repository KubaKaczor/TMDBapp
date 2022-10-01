package com.example.tmdbapp.domain.usecase

import com.example.tmdbapp.data.model.Movie
import com.example.tmdbapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetSavedMoviesUseCase(private val movieRepository: MovieRepository) {
    fun execute(): Flow<List<Movie>>? = movieRepository.getSavedMovies()
}