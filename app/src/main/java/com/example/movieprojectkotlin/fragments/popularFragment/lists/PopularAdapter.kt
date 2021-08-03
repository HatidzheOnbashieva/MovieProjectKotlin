package com.example.movieprojectkotlin.fragments.popularFragment.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.movieprojectkotlin.Model.Movie
import com.example.movieprojectkotlin.OnItemClickListener
import com.example.movieprojectkotlin.fragments.moviesFragment.MoviesInfoFragment
import com.example.movieprojectkotlin.R
import com.squareup.picasso.Picasso

class PopularAdapter(var _movies: List<Movie>) : RecyclerView.Adapter<PopularViewHolder>() {

    val movies
    get() = _movies

    init {
        this.movies
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_item, null)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holderInfo: PopularViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movies[position].imageUrl)
            .into(holderInfo.poster)
        holderInfo.title.text = movies[position].title

        val ctx = holderInfo.itemView.context

        holderInfo.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClickListener(view: View?, position: Int) {
                val movieID = movies[position].id
                val imageURL = movies[position].imageUrl
                val title = movies[position].title
                val overview = movies[position].overview
                val originalTitle = movies[position].originalTitle
                val rating = movies[position].rating
                val releaseDate = movies[position].releaseDate

                val fragment = MoviesInfoFragment()
                val bundle = Bundle()
                bundle.putInt("movieID", movieID)
                bundle.putString("imageURL", imageURL)
                bundle.putString("title", title)
                bundle.putString("overview", overview)
                bundle.putString("originalTitle", originalTitle)
                bundle.putDouble("rating", rating)
                bundle.putString("releaseDate", releaseDate)
                fragment.arguments = bundle

                val transaction: FragmentTransaction = (ctx as AppCompatActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.mainScreen, fragment).addToBackStack("moviesfragment").commit()

            }
        })
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}