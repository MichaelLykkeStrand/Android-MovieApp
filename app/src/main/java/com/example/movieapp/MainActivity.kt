package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.movieapp.api.ITheMovieDB
import com.example.movieapp.api.TheMovieDBClient
import com.example.movieapp.movie.MovieDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            //TODO change this to list view
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, MovieDetailsFragment.newInstance(), "movieDetails")
                .commit()
        }

        val apiService : ITheMovieDB = TheMovieDBClient.getClient()
        val call = apiService.getMovieDetails(527774)
        call.enqueue(object : Callback<MovieDetails> {
            override fun onFailure(call: Call<MovieDetails>?, t: Throwable?) {
                Log.d("retrofit", "call failed")
            }

            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>?) {
                Log.d("retrofit", "call success")
                val movieDetails = response?.body();
                Log.d("retrofit","movieDetails");
            }

        })

    }
}