package com.example.movieprojectkotlin.fragments.popularFragment.lists

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieprojectkotlin.OnItemClickListener
import com.example.movieprojectkotlin.R


class PopularViewHolder(view:View): RecyclerView.ViewHolder(view), View.OnClickListener{

    var poster: ImageView = itemView.findViewById(R.id.infoMoviePoster)
    var title: TextView = itemView.findViewById(R.id.infoMovieTitle)
    var itemClickListener: OnItemClickListener? = null

    init{
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        itemClickListener?.onItemClickListener(view, layoutPosition)
    }

    fun setOnItemClickListener(itemClick: OnItemClickListener){
        itemClickListener = itemClick
    }

}