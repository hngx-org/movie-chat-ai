package com.essycynthia.moviechat.domain.di

import android.content.Context
import com.essycynthia.moviechat.data.MovieApi
import com.essycynthia.moviechat.data.RetrofitWithCookie
import com.essycynthia.moviechat.domain.repository.MovieRepository
import com.essycynthia.moviechat.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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


    @Singleton
    @Provides
    fun provideRetrofitWithCookie(
        @ApplicationContext context: Context,
        gson: Gson
    ): Retrofit = RetrofitWithCookie(context, gson).createRetrofit()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") // used for parsing other responses
        .create()
}
