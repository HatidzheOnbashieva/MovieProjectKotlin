package com.example.movieprojectkotlin.fragments.favouritesFragment.lists

import androidx.recyclerview.widget.RecyclerView
import com.example.movieprojectkotlin.Model.Movie
import com.example.movieprojectkotlin.databinding.FavouritesMovieItemBinding
import com.squareup.picasso.Picasso

class FavouritesViewHolder(private val viewBinding: FavouritesMovieItemBinding): RecyclerView.ViewHolder(viewBinding.root) {

    fun setFavouritesRowData(rowData: Movie, onMovieRowClick: (Movie) -> Unit) {

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + rowData.imageUrl).into(viewBinding.infoMoviePoster)
        viewBinding.infoMovieTitle.text = rowData.title

        viewBinding.root.setOnClickListener {
            onMovieRowClick.invoke(rowData)
        }
    }
}

