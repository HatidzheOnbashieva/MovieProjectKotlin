package com.example.movieprojectkotlin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieprojectkotlin.fragments.favouritesFragment.FavouritesFragment
import com.example.movieprojectkotlin.fragments.popularFragment.PopularFragment
import com.example.movieprojectkotlin.fragments.topRatedFragment.TopRatedFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val numOfTabs: Int) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return numOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PopularFragment()
            1 -> TopRatedFragment()
            2 -> FavouritesFragment()
            else -> Fragment()
        }
    }

    fun reloadFavouritesFragment(): Fragment{
        return FavouritesFragment()
    }
}