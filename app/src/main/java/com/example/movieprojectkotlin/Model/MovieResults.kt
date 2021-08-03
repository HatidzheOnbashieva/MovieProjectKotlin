package com.example.movieprojectkotlin.Model

import com.google.gson.annotations.SerializedName

class MovieResults( @SerializedName("results") var _results: List<Movie> ) {

    val results
        get() = _results

    init{
        this.results
    }
}