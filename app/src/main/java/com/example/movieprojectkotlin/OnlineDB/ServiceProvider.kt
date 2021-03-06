package com.example.movieprojectkotlin.OnlineDB

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceProvider {

    class RetrofitInstance {

        val BASE_URL = "https://api.themoviedb.org/"

        fun getRetrofitInstance(): Retrofit {

            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}