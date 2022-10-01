package com.example.tmdbapp.domain.usecase

import com.example.tmdbapp.data.model.Movie
import com.example.tmdbapp.domain.repository.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(page: Int) : List<Movie>? = movieRepository.getMovies(page)
}