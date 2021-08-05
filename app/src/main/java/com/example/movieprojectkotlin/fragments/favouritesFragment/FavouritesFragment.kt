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

    private val onMovieRowClick : ((Movie) -> Unit) = {
        goToMoviesInfoFragment(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAdapter()
        movieViewModel = FavouritesViewModel(requireContext())

        movieViewModel.fetchMoviesList().observeOnce(viewLifecycleOwner, { movies ->
            updateFavouritesList(movies)
        })
    }

    private fun setUpAdapter(){
        viewBinding?.movieRecyclerViewFavourites?.layoutManager = GridLayoutManager(activity, 2)
        favouritesAdapter = FavouritesAdapter(onMovieRowClick)
        viewBinding?.movieRecyclerViewFavourites?.adapter = favouritesAdapter
    }

    private fun updateFavouritesList(movies: List<Movie>){
        favouritesAdapter.movies = movies as ArrayList<Movie>
        favouritesAdapter.notifyDataSetChanged()
    }

    fun favouritesTabSelected(){
        movieViewModel.fetchMoviesList().observe(viewLifecycleOwner, { movies ->
            updateFavouritesList(movies)
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

    private fun goToMoviesInfoFragment(movieData: Movie){
        val fragment = MoviesInfoFragment()
        val bundle = Bundle()
        bundle.putInt("movieID", movieData.id)
        bundle.putString("imageURL", movieData.imageUrl)
        bundle.putString("title", movieData.title)
        bundle.putString("overview", movieData.overview)
        bundle.putString("originalTitle", movieData.originalTitle)
        bundle.putDouble("rating", movieData.rating)
        bundle.putString("releaseDate", movieData.releaseDate)
        fragment.arguments = bundle

        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.mainScreen, fragment).addToBackStack("moviesPopularFragment").commit()
    }
}