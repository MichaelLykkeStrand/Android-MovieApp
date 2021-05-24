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
            supportFragmentManager
                .beginTransaction()
                    .replace(R.id.fragment_placeholder, MovieTitlesFragment.newInstance())
                    .commit()
        }
    }
}