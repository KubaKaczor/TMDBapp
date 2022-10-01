package com.example.tmdbapp.domain.usecase

import com.example.tmdbapp.data.model.Movie
import com.example.tmdbapp.domain.repository.MovieRepository

class DeleteSavedMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movie: Movie) = movieRepository.deleteMovie(movie)
}