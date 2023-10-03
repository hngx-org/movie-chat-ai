package com.essycynthia.moviechat.data

import android.content.Context
import android.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ActivityContext
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException
import java.util.*

class AddCookiesInterceptor(@ActivityContext context: Context?) : Interceptor {
    // We're storing our stuff in a database made just for cookies called PREF_COOKIES.
    // I reccomend you do this, and don't change this default value.
    private val context: Context?
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val preferences = PreferenceManager.getDefaultSharedPreferences(context).getStringSet(
            PREF_COOKIES, HashSet()
        ) as HashSet<String>?

        // Use the following if you need everything in one line.
        // Some APIs die if you do it differently.
        /*String cookiestring = "";
        for (String cookie : preferences) {
            String[] parser = cookie.split(";");
            cookiestring = cookiestring + parser[0] + "; ";
        }
        builder.addHeader("Cookie", cookiestring);
        */for (cookie in preferences!!) {
            builder.addHeader("Cookie", cookie)
            Timber.d("adding cookie %s", cookie)
        }
        return chain.proceed(builder.build())
    }

    companion object {
        const val PREF_COOKIES = "PREF_COOKIES"
    }

    init {
        this.context = context
    }
}