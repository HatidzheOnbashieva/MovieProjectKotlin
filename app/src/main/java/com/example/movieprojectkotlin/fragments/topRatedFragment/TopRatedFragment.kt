package com.example.movieprojectkotlin.fragments.topRatedFragment

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
import com.example.movieprojectkotlin.databinding.FragmentTopRatedBinding
import com.example.movieprojectkotlin.fragments.topRatedFragment.lists.TopRatedAdapter


class TopRatedFragment : Fragment() {


    private var viewBinding: FragmentTopRatedBinding? = null
    private lateinit var topRatedAdapter: TopRatedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentTopRatedBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var movieViewModel = TopRatedViewModel(requireContext())
        viewBinding?.movieRecyclerViewTopRated?.layoutManager = GridLayoutManager(activity, 2)

        movieViewModel.fetchTopRated().observeOnce(requireActivity(), Observer<List<Movie>> { movies ->
            setUpAdapter(movies)
        })
    }

    fun setUpAdapter(movies: List<Movie>){
        topRatedAdapter = TopRatedAdapter(movies)
        viewBinding?.movieRecyclerViewTopRated?.adapter = topRatedAdapter
        topRatedAdapter.notifyDataSetChanged()

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