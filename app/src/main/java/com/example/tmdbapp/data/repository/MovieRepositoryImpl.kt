package com.example.tmdbapp.data.repository

import android.util.Log
import com.example.tmdbapp.data.model.Movie
import com.example.tmdbapp.data.repository.dataSource.MoviesLocalDataSource
import com.example.tmdbapp.data.repository.dataSource.MoviesRemoteDataSource
import com.example.tmdbapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class MovieRepositoryImpl(
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val moviesRemoteDataSource: MoviesRemoteDataSource
): MovieRepository {

    override suspend fun getMovies(page: Int): List<Movie>? {
        lateinit var movieList: List<Movie>
        try {
            val response = moviesRemoteDataSource.getMovies(page)
            val body = response.body()
            if(body!=null){
                movieList = body.movies
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return movieList
    }

    override suspend fun getSearchedMovies(query: String): List<Movie>? {
        lateinit var movieList: List<Movie>
        try {
            val response = moviesRemoteDataSource.getSearchedMovies(query)
            val body = response.body()
            if(body!=null){
                movieList = body.movies
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return movieList
    }

    override fun getSavedMovies(): Flow<List<Movie>>? {
        return moviesLocalDataSource.getSavedMovies()
    }

    override suspend fun saveMovie(movie: Movie) {
        moviesLocalDataSource.saveMovieToDb(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        moviesLocalDataSource.deleteMovie(movie)
    }
}