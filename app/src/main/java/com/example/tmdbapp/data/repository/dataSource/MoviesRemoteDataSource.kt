package com.example.tmdbapp.data.repository.dataSource

import com.example.tmdbapp.data.model.MovieList
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getMovies(page: Int): Response<MovieList>
    suspend fun getSearchedMovies(query: String): Response<MovieList>
}