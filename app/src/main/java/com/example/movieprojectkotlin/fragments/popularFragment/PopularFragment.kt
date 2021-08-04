package com.example.movieprojectkotlin.fragments.popularFragment

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
import com.example.movieprojectkotlin.databinding.FragmentPopularBinding
import com.example.movieprojectkotlin.fragments.moviesFragment.MoviesInfoFragment
import com.example.movieprojectkotlin.fragments.popularFragment.lists.PopularAdapter


class PopularFragment : Fragment() {

    private var viewBinding: FragmentPopularBinding? = null
    private lateinit var popularAdapter: PopularAdapter

//    private val onMovieRowClick: ((Movie) -> Unit) = {
//       goToMoviesInfoFragment(it)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentPopularBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAdapter()
        var movieViewModel = PopularViewModel(requireContext())

        movieViewModel.fetchPopular()
            .observeOnce(requireActivity(), Observer<List<Movie>> { movies ->
              updateMoviesList(movies)
            })
    }

    private fun setUpAdapter(){
        viewBinding?.movieRecyclerViewPopular?.layoutManager = GridLayoutManager(activity, 2)
        popularAdapter = PopularAdapter{
            goToMoviesInfoFragment(it)
        }
        viewBinding?.movieRecyclerViewPopular?.adapter = popularAdapter
    }

    private fun updateMoviesList(movies: List<Movie>){
        popularAdapter.movies = movies as ArrayList<Movie>
        popularAdapter.notifyDataSetChanged()
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
