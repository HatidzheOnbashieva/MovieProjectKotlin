package com.example.movieprojectkotlin.fragments.popularFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieprojectkotlin.Model.Movie
import com.example.movieprojectkotlin.databinding.FragmentPopularBinding
import com.example.movieprojectkotlin.fragments.popularFragment.lists.PopularAdapter


class PopularFragment : Fragment() {

    private var viewBinding: FragmentPopularBinding? = null
    private lateinit var popularAdapter: PopularAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentPopularBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var movieViewModel = PopularViewModel(requireContext())
        viewBinding?.movieRecyclerViewPopular?.layoutManager = GridLayoutManager(activity, 2)

        movieViewModel.fetchPopular()
            .observeOnce(requireActivity(), Observer<List<Movie>> { movies ->
               setUpAdapter(movies)
            })
    }

    fun setUpAdapter(movies: List<Movie>){
        popularAdapter = PopularAdapter(movies)
        viewBinding?.movieRecyclerViewPopular?.adapter = popularAdapter
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
}
