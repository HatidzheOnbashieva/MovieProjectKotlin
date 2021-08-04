package com.example.movieprojectkotlin.fragments.popularFragment.lists

import androidx.recyclerview.widget.RecyclerView
import com.example.movieprojectkotlin.Model.Movie
import com.example.movieprojectkotlin.databinding.PopularMovieItemBinding
import com.squareup.picasso.Picasso


class PopularViewHolder(private val viewBinding: PopularMovieItemBinding): RecyclerView.ViewHolder(viewBinding.root) {

    fun setPopularRowData(movieRowData: Movie, onMovieRowClick: (Movie) -> Unit){
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movieRowData.imageUrl).into(viewBinding.infoMoviePoster)
        viewBinding.infoMovieTitle.text = movieRowData.title

        viewBinding.root.setOnClickListener {
            onMovieRowClick.invoke(movieRowData)
        }
    }

}