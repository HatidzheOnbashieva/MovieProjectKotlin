package com.example.movieprojectkotlin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieprojectkotlin.fragments.favouritesFragment.FavouritesFragment
import com.example.movieprojectkotlin.fragments.popularFragment.PopularFragment
import com.example.movieprojectkotlin.fragments.topRatedFragment.TopRatedFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, val _numOfTabs: Int?) : FragmentStateAdapter(fragmentManager, lifecycle) {

    val numOfTabs
        get() = _numOfTabs ?: 0

    init {
        this.numOfTabs
    }

    override fun getItemCount(): Int {
        return numOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return PopularFragment()
            1 -> return TopRatedFragment()
            2 -> return FavouritesFragment()
            else -> return Fragment()
        }
    }

    fun reloadFavouritesFragment(): Fragment{
        return FavouritesFragment()
    }
}