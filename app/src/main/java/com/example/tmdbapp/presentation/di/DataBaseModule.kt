package com.example.tmdbapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.tmdbapp.data.db.MovieDao
import com.example.tmdbapp.data.db.TMDBDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideTMDBDatabase(app: Application): TMDBDatabase {
        return Room.databaseBuilder(app, TMDBDatabase::class.java, "tmdb_app_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase): MovieDao {
        return tmdbDatabase.movieDao()
    }


}