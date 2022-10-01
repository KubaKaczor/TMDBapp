package com.example.tmdbapp.domain.usecase

import com.example.tmdbapp.data.model.Movie
import com.example.tmdbapp.domain.repository.MovieRepository

class GetSearchedMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(query: String) : List<Movie>? = movieRepository.getSearchedMovies(query)
}