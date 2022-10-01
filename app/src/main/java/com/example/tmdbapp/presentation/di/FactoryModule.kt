package com.example.tmdbapp.presentation.di

import android.app.Application
import com.example.tmdbapp.domain.usecase.*
import com.example.tmdbapp.presentation.viewmodel.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideMovieViewModelFactory(
        application: Application,
        deleteSavedMovieUseCase: DeleteSavedMovieUseCase,
        getMoviesUseCase: GetMoviesUseCase,
        getSavedMoviesUseCase: GetSavedMoviesUseCase,
        saveMovieUseCase: SaveMovieUseCase,
        getSearchedMoviesUseCase: GetSearchedMoviesUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(
            application,
            deleteSavedMovieUseCase,
            getMoviesUseCase,
            getSavedMoviesUseCase,
            saveMovieUseCase,
            getSearchedMoviesUseCase
        )
    }

}