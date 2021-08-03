package com.example.movieprojectkotlin.fragments.topRatedFragment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieprojectkotlin.Model.Movie
import com.example.movieprojectkotlin.Model.MovieResults
import com.example.movieprojectkotlin.OnlineDB.API
import com.example.movieprojectkotlin.OnlineDB.ServiceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedViewModel(context: Context) : ViewModel(){

    var movieList: MutableLiveData<List<Movie>> = MutableLiveData()
    var movieApi: API =
        ServiceProvider.RetrofitInstance().getRetrofitInstance().create(API::class.java)

    private var API_KEY = "d427f7ee4cda19464d0ffca2218445be"
    private var LANGUAGE = "en-US"
    private var PAGE = 1
    private var CATEGORY2 = "top_rated"

    fun fetchTopRated() : LiveData<List<Movie>>{
        val call1: Call<MovieResults>? = movieApi.getMovies(CATEGORY2, API_KEY, LANGUAGE, PAGE)
        call1?.enqueue(object : Callback<MovieResults> {
            override fun onResponse(call: Call<MovieResults>, response: Response<MovieResults>) {
                val movieResults = response.body()
                val movies = movieResults?.results
                movieList.postValue(movies)
            }

            override fun onFailure(call: Call<MovieResults>, t: Throwable) {
                t.message
            }

        })
        return movieList
    }
}