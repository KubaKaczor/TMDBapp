package com.example.tmdbapp.data.repository.dataSourceImpl

import com.example.tmdbapp.data.db.MovieDao
import com.example.tmdbapp.data.model.Movie
import com.example.tmdbapp.data.repository.dataSource.MoviesLocalDataSource
import kotlinx.coroutines.flow.Flow

class MoviesLocalDataSourceImpl(val movieDao : MovieDao): MoviesLocalDataSource {
    override suspend fun saveMovieToDb(movie: Movie) {
        movieDao.insert(movie)
    }

    override fun getSavedMovies(): Flow<List<Movie>> {
        return movieDao.getMovies()
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDao.delete(movie)
    }
}