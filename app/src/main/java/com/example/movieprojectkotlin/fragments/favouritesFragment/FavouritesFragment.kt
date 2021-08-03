package com.example.movieprojectkotlin.fragments.favouritesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieprojectkotlin.Model.Movie
import com.example.movieprojectkotlin.R
import com.example.movieprojectkotlin.databinding.FragmentFavouritesBinding
import com.example.movieprojectkotlin.fragments.favouritesFragment.lists.FavouritesAdapter
import com.example.movieprojectkotlin.fragments.moviesFragment.MoviesInfoFragment


class FavouritesFragment : Fragment() {

    private var viewBinding: FragmentFavouritesBinding? = null
    private lateinit var favouritesAdapter: FavouritesAdapter
    private lateinit var movieViewModel: FavouritesViewModel

    //Callback
    //Callback hell
    //Android apprentice
    //Kotlin apprentice
    private val onMovieRowClick : ((Movie) -> Unit) = {
        val fragment = MoviesInfoFragment()
        val bundle = Bundle()
        bundle.putInt("movieID", it.id)
        bundle.putString("imageURL", it.imageUrl)
        bundle.putString("title", it.title)
        bundle.putString("overview", it.overview)
        bundle.putString("originalTitle", it.originalTitle)
        bundle.putDouble("rating", it.rating)
        bundle.putString("releaseDate", it.releaseDate)
        fragment.arguments = bundle

        val transaction: FragmentTransaction =  parentFragmentManager.beginTransaction()
        transaction.replace(R.id.mainScreen, fragment).addToBackStack("moviesfragment").commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding?.movieRecyclerViewFavourites?.layoutManager = GridLayoutManager(activity, 2)
        movieViewModel = FavouritesViewModel(requireContext())
        movieViewModel.fetchMoviesList().observeOnce(viewLifecycleOwner, Observer<List<Movie>> { movies ->
            if(movies != null){
            favouritesAdapter = FavouritesAdapter(movies, onMovieRowClick)
            viewBinding?.movieRecyclerViewFavourites?.adapter = favouritesAdapter
            }
        })
    }

    fun updateList(){
        movieViewModel = FavouritesViewModel(requireContext())
        movieViewModel.fetchMoviesList().observeOnce(viewLifecycleOwner, Observer<List<Movie>> { movies ->
            favouritesAdapter.updateAdapter(movies)
        })
    }

    private fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }
}