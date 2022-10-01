package com.example.tmdbapp.data.repository.dataSourceImpl

import com.example.tmdbapp.data.api.TMDBService
import com.example.tmdbapp.data.model.MovieList
import com.example.tmdbapp.data.repository.dataSource.MoviesRemoteDataSource
import retrofit2.Response

class MoviesRemoteDataSourceImpl(private val tmdbService: TMDBService) : MoviesRemoteDataSource {
    override suspend fun getMovies(page: Int): Response<MovieList> {
        return  tmdbService.getLatestMovies(page = page)
    }

    override suspend fun getSearchedMovies(query: String): Response<MovieList> {
        return tmdbService.getSearchedMovies(query = query)
    }
}