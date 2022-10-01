package com.example.tmdbapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.tmdbapp.data.model.Movie
import com.example.tmdbapp.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieViewModel(
    private val app: Application,
    private val deleteSavedMovieUseCase: DeleteSavedMovieUseCase,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val getSearchedMovies: GetSearchedMoviesUseCase
): AndroidViewModel(app) {

    val movies: MutableLiveData<List<Movie>?> = MutableLiveData()

//    fun getMovies(page: Int) = liveData {
//        val movieList = getMoviesUseCase.execute(page)
//        emit(movieList)
//    }

    fun getMovies(page: Int) = viewModelScope.launch(Dispatchers.IO) {
        val apiResult = getMoviesUseCase.execute(page)
        movies.postValue(apiResult)
    }

    fun getSavedMovies() = liveData {
        val movieList = getSavedMoviesUseCase.execute()?.collect{
            emit(it)
        }
    }

    fun getSearchedMovies(query: String) = liveData{
        val searchedMovies = getSearchedMovies.execute(query)
        emit(searchedMovies)
    }


    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    fun saveMovie(movie: Movie) = viewModelScope.launch {
        saveMovieUseCase.execute(movie)
    }

    fun deleteMovie(movie: Movie) = viewModelScope.launch {
        deleteSavedMovieUseCase.execute(movie)
    }
}