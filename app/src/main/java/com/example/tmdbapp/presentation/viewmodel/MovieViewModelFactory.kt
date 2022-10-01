package com.example.tmdbapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbapp.domain.usecase.*

class MovieViewModelFactory(
    private val app: Application,
    private val deleteSavedMovieUseCase: DeleteSavedMovieUseCase,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val getSearchedMoviesUseCase: GetSearchedMoviesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(
            app,
            deleteSavedMovieUseCase,
            getMoviesUseCase,
            getSavedMoviesUseCase,
            saveMovieUseCase,
            getSearchedMoviesUseCase
        ) as T
    }
}