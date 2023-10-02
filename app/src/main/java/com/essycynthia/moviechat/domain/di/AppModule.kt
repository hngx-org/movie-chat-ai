package com.essycynthia.moviechat.domain.di

import com.essycynthia.moviechat.data.MovieApi
import com.essycynthia.moviechat.domain.repository.MovieRepository
import com.essycynthia.moviechat.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideRepository( api: MovieApi) : MovieRepository{
        return MovieRepository(api)
    }
    @Provides
    @Singleton
    fun provideRetrofitSetup() : MovieApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}