package com.example.movieprojectkotlin.fragments.favouritesFragment.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieprojectkotlin.Model.Movie
import com.example.movieprojectkotlin.databinding.FavouritesMovieItemBinding

class FavouritesAdapter(private val onMovieRowClick: (Movie) -> Unit): RecyclerView.Adapter<FavouritesViewHolder>() {

    var movies: ArrayList<Movie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val viewBinding = FavouritesMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FavouritesViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val movieData = movies[position]

        holder.setFavouritesRowData(movieData, onMovieRowClick)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
