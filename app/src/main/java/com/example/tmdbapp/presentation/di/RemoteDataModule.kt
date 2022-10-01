package com.example.tmdbapp.presentation.di

import com.example.tmdbapp.data.api.TMDBService
import com.example.tmdbapp.data.repository.dataSource.MoviesRemoteDataSource
import com.example.tmdbapp.data.repository.dataSourceImpl.MoviesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(
        tmdbService: TMDBService
    ):MoviesRemoteDataSource{
        return MoviesRemoteDataSourceImpl(tmdbService)
    }

}