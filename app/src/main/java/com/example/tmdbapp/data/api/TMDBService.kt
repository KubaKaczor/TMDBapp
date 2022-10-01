package com.example.tmdbapp.data.api

import com.example.tmdbapp.BuildConfig
import com.example.tmdbapp.data.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/top_rated")
    suspend fun getLatestMovies(
        @Query(
            "api_key"
        ) apiKey: String = BuildConfig.API_KEY,
        @Query("page")
        page:Int,
        @Query(
            "language"
        ) language: String = "pl-PL"
    ): Response<MovieList>

    @GET("search/movie")
    suspend fun getSearchedMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("page")
        page:Int = 1,
        @Query("language")
        language: String = "pl-PL",
        @Query("query")
        query: String
    ): Response<MovieList>

}