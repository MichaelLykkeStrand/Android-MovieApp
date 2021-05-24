package com.example.movieapp.api

import com.example.movieapp.api.ITheMovieDB
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_KEY = "acfa9c42a301001e530d28c8bf25e0ce"
const val BASE_URL = "https://api.themoviedb.org/3/"

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

//Object is a shorthand for singleton behaviour
object TheMovieDBClient {

    fun getClient(): ITheMovieDB {

        val requestInterceptor = Interceptor { chain -> //Lambda


            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ITheMovieDB::class.java)

    }
}