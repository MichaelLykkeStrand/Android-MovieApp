package com.example.movieapp.api

import com.example.movieapp.movie.MovieDetails
import com.example.movieapp.movie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ITheMovieDB {
    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Call<MovieDetails>
}