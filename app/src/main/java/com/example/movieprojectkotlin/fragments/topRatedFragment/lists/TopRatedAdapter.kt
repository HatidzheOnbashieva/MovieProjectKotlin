package com.example.movieprojectkotlin.fragments.topRatedFragment.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.movieprojectkotlin.Model.Movie
import com.example.movieprojectkotlin.OnItemClickListener
import com.example.movieprojectkotlin.R
import com.example.movieprojectkotlin.fragments.moviesFragment.MoviesInfoFragment
import com.example.movieprojectkotlin.fragments.popularFragment.lists.PopularViewHolder
import com.squareup.picasso.Picasso

class TopRatedAdapter(var _movies: List<Movie>): RecyclerView.Adapter<TopRatedViewHolder>() {

    val movies
        get() = _movies

    init {
        this.movies
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.top_rated_item_view, null)
        return TopRatedViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movies[position].imageUrl)
            .into(holder.poster)
        holder.title.text = movies[position].title

        val ctx = holder.itemView.context

        holder.setOnItemClickListener(object : OnItemClickListener {
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