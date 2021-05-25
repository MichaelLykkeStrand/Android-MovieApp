package com.example.movieapp.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.movie.Movie
import com.example.movieapp.R

class MovieListAdapter(private val dataSet: MutableList<Movie>, callbackListener: OnMovieClickListener) :
        RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    val listener : OnMovieClickListener = callbackListener

    //ViewHolder
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTextView: TextView
        init {
            movieTextView = itemView.findViewById(R.id.movieTitleTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_view_holder, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = dataSet.size //Shorthand returning the value of dataSet.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieTextView.text = dataSet[position].title
        holder.itemView.setOnClickListener {
            listener.onMovieClick(dataSet[position])
        }
    }

}