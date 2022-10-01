package com.example.tmdbapp.data.db

import androidx.room.*
import com.example.tmdbapp.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM top_rated_movies")
    fun getMovies(): Flow<List<Movie>>
}