package com.example.movieprojectkotlin.fragments.popularFragment.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieprojectkotlin.Model.Movie
import com.example.movieprojectkotlin.databinding.PopularMovieItemBinding

class PopularAdapter(var onMovieRowClick: (Movie) -> Unit) : RecyclerView.Adapter<PopularViewHolder>() {
    var movies: ArrayList<Movie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val viewBinding = PopularMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PopularViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        //val ctx = holderInfo.itemView.context
        val movieData = movies[position]
        holder.setPopularRowData(movieData, onMovieRowClick)

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}