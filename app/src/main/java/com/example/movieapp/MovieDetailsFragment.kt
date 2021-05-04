package com.example.movieapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.movieapp.api.ITheMovieDB
import com.example.movieapp.api.TheMovieDBClient
import com.example.movieapp.movie.MovieDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_movie_details, container, false)
        //Update the fragment with information from the context
        // Inflate the layout for this fragment
        val movieID = arguments?.getInt("movieID");
        val apiService : ITheMovieDB = TheMovieDBClient.getClient()
        val call = apiService.getMovieDetails(movieID)
        call.enqueue(object : Callback<MovieDetails> {
            override fun onFailure(call: Call<MovieDetails>?, t: Throwable?) {
                Log.d("retrofit", "call failed")
            }

            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>?) {
                Log.d("retrofit", "call success")
                val movieDetails = response?.body();
                if (movieDetails != null) {
                    val nametextview = view.findViewById<TextView>(R.id.textView6)
                    nametextview.setText(movieDetails.original_title);
                }

            }

        })
        Log.d("debug","called!")
        return view;
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context != null){

        }
    }


}