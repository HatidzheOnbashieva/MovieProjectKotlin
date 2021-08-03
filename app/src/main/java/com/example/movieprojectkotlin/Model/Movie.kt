package com.example.movieprojectkotlin.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @SerializedName("id") var _id: Int?,
    @SerializedName("title") var _title: String?,
    @SerializedName("overview") var _overview: String?,
    @SerializedName("original_title") var _originalTitle: String?,
    @SerializedName("vote_average") var _rating: Double?,
    @SerializedName("release_date") var _releaseDate: String?,
    @SerializedName("poster_path") var _imageURL: String?
) : Serializable {


    val id
        get() = _id ?: 0
    val title
        get() = _title ?: ""
    val overview
        get() = _overview ?: ""
    val originalTitle
        get() = _originalTitle ?: ""
    val rating
        get() = _rating ?: 0.0
    val releaseDate
        get() = _releaseDate ?: ""
    val imageUrl
        get() = _imageURL ?: ""

    init{
        this.id
        this.title
        this.overview
        this.originalTitle
        this.rating
        this.releaseDate
        this.imageUrl
    }

}