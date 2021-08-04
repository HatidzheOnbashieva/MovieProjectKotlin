package com.example.movieprojectkotlin.fragments.favouritesFragment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieprojectkotlin.DB.DataBaseConnectivity
import com.example.movieprojectkotlin.Model.Movie

class FavouritesViewModel(context: Context) : ViewModel() {

    var movieList: MutableLiveData<List<Movie>> = MutableLiveData()
    var dbConnection: DataBaseConnectivity = DataBaseConnectivity(context)

    fun fetchMoviesList() : LiveData<List<Movie>> {
        val data = dbConnection.getContents()
        var movies = arrayListOf<Movie>()

        if (data.count != 0) {
            while (data.moveToNext()) {
                movies.add(
                    Movie(
                        data.getInt(0),
                        data.getString(1),
                        data.getString(2),
                        data.getString(3),
                        data.getDouble(4),
                        data.getString(5),
                        data.getString(6)
                    )
                )
                movieList.postValue(movies)
            }

        }
        else{
            movieList.postValue(movies) // this line of code prevents the app from crashing when the list is empty
        }


        return movieList
    }
}