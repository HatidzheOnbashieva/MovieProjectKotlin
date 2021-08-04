package com.example.movieprojectkotlin.fragments.topRatedFragment.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieprojectkotlin.Model.Movie
import com.example.movieprojectkotlin.databinding.TopRatedItemViewBinding

class TopRatedAdapter(private val onMovieRowClick: (Movie) -> Unit): RecyclerView.Adapter<TopRatedViewHolder>() {

    var movies: ArrayList<Movie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val viewBinding = TopRatedItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopRatedViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {

        //val ctx = holder.itemView.context
        val movieData = movies[position]
        holder.setTopRatedRowData(movieData, onMovieRowClick)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}