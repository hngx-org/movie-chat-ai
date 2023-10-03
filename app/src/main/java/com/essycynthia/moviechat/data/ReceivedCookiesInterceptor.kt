package com.essycynthia.moviechat.data

import android.content.Context
import android.preference.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

// Original written by tsuharesu
// Adapted to create a "drop it in and watch it work" approach by Nikhil Jha.
// Just add your package statement and drop it in the folder with all your other classes.
class ReceivedCookiesInterceptor(context: Context?) : Interceptor {
    private val context: Context?
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            val cookies = PreferenceManager.getDefaultSharedPreferences(context)
                .getStringSet("PREF_COOKIES", HashSet()) as HashSet<String>?
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies!!.add(header)
            }
            val memes = PreferenceManager.getDefaultSharedPreferences(context).edit()
            memes.putStringSet("PREF_COOKIES", cookies).apply()
            memes.commit()
        }
        return originalResponse
    }

    init {
        this.context = context
    } // AddCookiesInterceptor()
}