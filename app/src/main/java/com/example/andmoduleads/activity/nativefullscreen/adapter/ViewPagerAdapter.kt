package com.example.andmoduleads.activity.nativefullscreen.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    activity: FragmentActivity,
    private val listPage: List<Fragment>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return listPage.size
    }

    override fun createFragment(position: Int): Fragment {
        return listPage[position]
    }
}