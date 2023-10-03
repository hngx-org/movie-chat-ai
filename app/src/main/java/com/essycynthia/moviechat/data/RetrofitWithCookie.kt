package com.essycynthia.moviechat.data

import android.content.Context
import com.essycynthia.moviechat.util.Constants
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
class RetrofitWithCookie @Inject constructor(
    context: Context, // uses Hilt to inject the context to be passed to the interceptors
    gson: Gson
) {
    private val mContext = context
    private val gson = gson

    fun createRetrofit(): Retrofit {
        val client: OkHttpClient
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(AddCookiesInterceptor(mContext)) // VERY VERY IMPORTANT
        builder.addInterceptor(ReceivedCookiesInterceptor(mContext)) // VERY VERY IMPORTANT
        client = builder.build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // REQUIRED
            .client(client) // VERY VERY IMPORTANT
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build() // REQUIRED
    }
}