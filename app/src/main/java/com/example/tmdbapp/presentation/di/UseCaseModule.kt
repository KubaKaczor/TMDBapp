package com.example.tmdbapp.presentation.di

import com.example.tmdbapp.domain.repository.MovieRepository
import com.example.tmdbapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideDeleteSavedMovieUseCase(
        movieRepository: MovieRepository
    ):DeleteSavedMovieUseCase{
        return DeleteSavedMovieUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideGetMoviesUseCase(
        movieRepository: MovieRepository
    ):GetMoviesUseCase{
        return GetMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedMoviesUseCase(
        movieRepository: MovieRepository
    ):GetSearchedMoviesUseCase{
        return GetSearchedMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedMoviesNewsUseCase(
        movieRepository: MovieRepository
    ):GetSavedMoviesUseCase{
        return GetSavedMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideSaveMovieUseCase(
        movieRepository: MovieRepository
    ):SaveMovieUseCase{
        return SaveMovieUseCase(movieRepository)
    }

}