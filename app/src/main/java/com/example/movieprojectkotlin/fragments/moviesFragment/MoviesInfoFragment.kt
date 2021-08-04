package com.example.movieprojectkotlin.fragments.moviesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.movieprojectkotlin.Model.Movie
import com.example.movieprojectkotlin.R
import com.example.movieprojectkotlin.databinding.FragmentMoviesInfoBinding
import com.squareup.picasso.Picasso


class MoviesInfoFragment : Fragment() {


    private var viewBinding: FragmentMoviesInfoBinding? = null
    private var favouriteFlag: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentMoviesInfoBinding.inflate(inflater,container, false)
        return viewBinding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var moviesInfoViewModel = MovieInfoViewModel(requireContext())
        var movieViewModel = MovieInfoViewModel(requireContext())

        var movieID: Int = this.requireArguments().getInt("movieID")
        var imageUrl: String? = this.requireArguments().getString("imageURL")
        var movieTitle:String? = this.requireArguments().getString("title")
        var movieOverview:String? = this.requireArguments().getString("overview")
        var movieOriginalTitle:String? = this.requireArguments().getString("originalTitle")
        var movieRating: Double? = this.requireArguments().getDouble("rating", 2.0)
        var movieReleaseDate:String? = this.requireArguments().getString("releaseDate")

        Picasso.get().load("https://image.tmdb.org/t/p/w500$imageUrl").into(viewBinding?.moviePoster)
        viewBinding?.movieTitle?.text = movieTitle
        viewBinding?.movieOverview?.text = movieOverview

        viewBinding?.movieOriginalTitle?.text = movieOriginalTitle
        viewBinding?.movieRating?.text = movieRating.toString()
        viewBinding?.movieReleaseDate?.text = movieReleaseDate

        movieViewModel.fetchIDList().observeOnce(viewLifecycleOwner, Observer<List<Int>>{ ids->
            if(ids.contains(movieID)){
                favouriteFlag = true
                viewBinding?.favourite?.setImageResource(R.drawable.ic_favourite)
            }
        })

        viewBinding?.back?.setOnClickListener(View.OnClickListener {

            parentFragmentManager.popBackStack()

        })

        viewBinding?.favourite?.setOnClickListener(View.OnClickListener {

            val movie = Movie(
                movieID,
                movieTitle,
                movieOverview,
                movieOriginalTitle,
                movieRating,
                movieReleaseDate,
                imageUrl
            )

            if(favouriteFlag){
                moviesInfoViewModel.removeItem(movieID, requireContext())
                viewBinding?.favourite?.setImageResource(R.drawable.ic_favorite_nofill)
                favouriteFlag = false

            }else{
                moviesInfoViewModel.addDataToLocalDB(movie,requireContext())
                viewBinding?.favourite?.setImageResource(R.drawable.ic_favourite)
                favouriteFlag = true
            }
//
//            movieViewModel.fetchTitlesList().observeOnce(viewLifecycleOwner, Observer<List<String>>{ titles->
//                if(titles.isEmpty()) {
//                    moviesInfoViewModel.addDataToLocalDB(movie, requireContext())
//                    viewBinding?.favourite?.setImageResource(R.drawable.ic_favourite)
//                }
//                else{
//                    if(titles.contains(movieTitle)) {
//                        movieViewModel.fetchIDList().observeOnce(viewLifecycleOwner, { ids ->
//                            if(ids.contains(movieID)){
//                                moviesInfoViewModel.removeItem(movieID, requireContext())
//                                viewBinding?.favourite?.setImageResource(R.drawable.ic_favorite_nofill)
//                            }
//                        })
//                    }
//                    else {
//                        moviesInfoViewModel.addDataToLocalDB(movie, requireContext())
//                        viewBinding?.favourite?.setImageResource(R.drawable.ic_favourite)
//                    }
//                }
//            })
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