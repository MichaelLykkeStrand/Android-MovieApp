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
import com.example.movieapp.movie.Movie
import com.example.movieapp.movie.adapter.MovieListAdapter
import com.example.movieapp.movie.adapter.OnMovieClickListener


class MovieTitlesFragment : Fragment(), OnMovieClickListener {

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
        val movieList = mutableListOf<Movie>()
        movieList.add(Movie(1,"path","today","movie1"))
        movieList.add(Movie(2,"path","today","movie2"))
        movieList.add(Movie(3,"path","today","movie3"))
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_movie_titles, container, false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.movie_titles_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = MovieListAdapter(movieList,this)
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