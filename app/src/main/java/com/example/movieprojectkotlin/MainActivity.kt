package com.example.movieprojectkotlin

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movieprojectkotlin.databinding.ActivityMainBinding
import com.example.movieprojectkotlin.fragments.favouritesFragment.FavouritesFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private var viewBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        //val am: ActivityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        //Log.d("XXX", "dalvik.vm.heapgrowthlimit: " + am.memoryClass)
        //Log.d("XXX", "dalvik.vm.heapsize: " + am.largeMemoryClass)

       val pagerAdapter = PagerAdapter(supportFragmentManager, lifecycle, 3)

        viewBinding?.viewPager?.adapter = pagerAdapter
        viewBinding?.tabBar?.let {
            viewBinding?.viewPager?.let { it1 ->
                TabLayoutMediator(it, it1){
                    tab, position ->
                    when(position){
                        0 -> tab.text = "Popular"
                        1 -> tab.text = "Top Rated"
                        2 -> tab.text = "Favourites"
                    }
                }
            }
        }?.attach()

        viewBinding?.tabBar?.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

                val fragment = supportFragmentManager.findFragmentByTag("f2")

                if (tab?.position == 2 && fragment != null) {
                    (fragment as FavouritesFragment).favouritesTabSelected()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }


}