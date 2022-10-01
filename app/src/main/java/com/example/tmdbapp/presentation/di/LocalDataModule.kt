package com.example.tmdbapp.presentation.di

import com.example.tmdbapp.data.db.MovieDao
import com.example.tmdbapp.data.repository.dataSource.MoviesLocalDataSource
import com.example.tmdbapp.data.repository.dataSourceImpl.MoviesLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(movieDao: MovieDao):MoviesLocalDataSource{
        return MoviesLocalDataSourceImpl(movieDao)
    }

}