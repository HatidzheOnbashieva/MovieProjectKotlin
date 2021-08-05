package com.example.movieprojectkotlin.fragments.moviesFragment

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieprojectkotlin.DB.DataBaseConnectivity
import com.example.movieprojectkotlin.Model.Movie

class MovieInfoViewModel(val context: Context) : ViewModel() {

    var dataBaseConnectivity: DataBaseConnectivity = DataBaseConnectivity(context)
    //var movieIDList: MutableLiveData<List<Int>> = MutableLiveData()
    var valueOfId: MutableLiveData<Boolean> = MutableLiveData()


    fun addDataToLocalDB(movie: Movie, context: Context){
        var result: Boolean = dataBaseConnectivity.addData(movie.id, movie.title, movie.overview, movie.originalTitle, movie.rating, movie.releaseDate, movie.imageUrl)
        if(result == false){
            Toast.makeText(context.applicationContext, "Unsuccessful data insert in the database!", Toast.LENGTH_LONG)
                .show()
        }
        else{
            Toast.makeText(
                context.applicationContext,
                "You successfully added this movie to your favourites list!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun removeItem(movieID: Int, context: Context) {
        var result: Boolean = dataBaseConnectivity.removeData(movieID)
        if(result == false){
            Toast.makeText(context.applicationContext, "Unsuccessful remove!", Toast.LENGTH_LONG)
                .show()
        }
        else{
            Toast.makeText(
                context.applicationContext,
                "Successfully removed item!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

//    fun fetchIDList() : LiveData<List<Int>> {
//        val data = dataBaseConnectivity.getContents()
//        val movieID: ArrayList<Int> = arrayListOf()
//
//        if (data.count != 0) {
//            while (data.moveToNext()) {
//                movieID.add(data.getInt(0))
//            }
//        }
//        movieIDList.postValue(movieID)
//
//        return movieIDList
//    }


    fun fetchID(movieID: Int) : LiveData<Boolean> {
        val idCheckedValue = dataBaseConnectivity.checkIfDBContainsId(movieID)

        if(idCheckedValue){
            valueOfId.postValue(idCheckedValue)
        }

        return valueOfId
    }
}