package com.example.movieapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.api.ITheMovieDB
import com.example.movieapp.api.TheMovieDBClient
import com.example.movieapp.movie.Movie
import com.example.movieapp.movie.MovieDetails
import com.example.movieapp.movie.MovieResponse
import com.example.movieapp.movie.adapter.MovieListAdapter
import com.example.movieapp.movie.adapter.OnMovieClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieTitlesFragment : Fragment(), OnMovieClickListener {

    val apiService : ITheMovieDB = TheMovieDBClient.getClient()

    companion object {
        fun newInstance(): MovieTitlesFragment {
            return MovieTitlesFragment()
        }

        const val BACKSTACKNAME = "movieDetails"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_movie_titles, container, false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.movie_titles_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        val movieList = mutableListOf<Movie>()
        recyclerView.adapter = MovieListAdapter(movieList,this)


        apiService.getPopularMovie(1);
        val call = apiService.getPopularMovie(1)

        call.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                Log.d("retrofit", "call failed");
            }
            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                Log.d("retrofit", "call success");
                val movieResponse = response?.body();
                if (movieResponse != null) {
                    movieResponse.movieList.forEach{
                        movieList.add(it);
                        Log.d("retrofit",movieList.toString());
                    }
                    //update view
                    (recyclerView.adapter as MovieListAdapter).notifyDataSetChanged();
                }
            }
        })

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context != null){
            //Update the fragment with information from the context
        }
    }

    override fun onMovieClick(movie: Movie) {
        Log.d("callback","movie is back")
        val fr = getFragmentManager()?.beginTransaction()
        fr?.replace(R.id.fragment_placeholder, MovieDetailsFragment())
        fr?.addToBackStack(BACKSTACKNAME)
        fr?.commit()
    }

}