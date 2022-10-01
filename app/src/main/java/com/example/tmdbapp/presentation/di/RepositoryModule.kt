package com.example.tmdbapp.presentation.di

import com.example.tmdbapp.data.repository.MovieRepositoryImpl
import com.example.tmdbapp.data.repository.dataSource.MoviesLocalDataSource
import com.example.tmdbapp.data.repository.dataSource.MoviesRemoteDataSource
import com.example.tmdbapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MoviesRemoteDataSource,
        movieLocalDataSource: MoviesLocalDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieLocalDataSource,
            movieRemoteDataSource
        )
    }

}