package com.example.tmdbapp.presentation.di

import com.example.tmdbapp.data.api.TMDBService
import com.example.tmdbapp.presentation.adapter.MovieAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideAdapter(): MovieAdapter {
        return MovieAdapter()
    }
}