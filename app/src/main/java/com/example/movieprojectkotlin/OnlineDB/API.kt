package com.example.movieprojectkotlin.OnlineDB

import com.example.movieprojectkotlin.Model.MovieResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("3/movie/{category}")
    fun getMovies(
        @Path("category") category: String?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int
    ): Call<MovieResults>?
}