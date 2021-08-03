package com.example.movieprojectkotlin.fragments.topRatedFragment.lists

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieprojectkotlin.OnItemClickListener
import com.example.movieprojectkotlin.R

class TopRatedViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

    var poster: ImageView
    var title: TextView
    var itemClickListener: OnItemClickListener? = null

    init{
        poster = itemView.findViewById(R.id.infoMoviePoster)
        title = itemView.findViewById(R.id.infoMovieTitle)
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        itemClickListener?.onItemClickListener(view, layoutPosition)
    }

    fun setOnItemClickListener(itemClick: OnItemClickListener){
        itemClickListener = itemClick
    }

}