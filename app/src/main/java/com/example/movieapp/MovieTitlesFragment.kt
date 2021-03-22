package com.example.movieapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.movie.adapter.MovieListAdapter


class MovieTitlesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_movie_titles, container, false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.movie_titles_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = MovieListAdapter(activity)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context != null){
            //Update the fragment with information from the context
        }
    }

}