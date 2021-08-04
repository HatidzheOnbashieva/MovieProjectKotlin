package com.example.movieprojectkotlin.fragments.topRatedFragment.lists

import androidx.recyclerview.widget.RecyclerView
import com.example.movieprojectkotlin.Model.Movie
import com.example.movieprojectkotlin.databinding.TopRatedItemViewBinding
import com.squareup.picasso.Picasso

class TopRatedViewHolder(private val viewBinding: TopRatedItemViewBinding): RecyclerView.ViewHolder(viewBinding.root) {

    fun setTopRatedRowData(movieRowData: Movie, onMovieRowClick: (Movie) -> Unit){
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movieRowData.imageUrl).into(viewBinding.infoMoviePoster)
        viewBinding.infoMovieTitle.text = movieRowData.title

        viewBinding.root.setOnClickListener {
            onMovieRowClick.invoke(movieRowData)
        }
    }
}