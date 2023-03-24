package com.example.lifeplanner.feature.weekly

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lifeplanner.base.BaseFragment

class EachDayViewPagerAdapter(
    private val fragmentList: ArrayList<WeeklyEachDayFragment>,
    fragment: BaseFragment
) : FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment = fragmentList[position]
    override fun getItemCount(): Int = fragmentList.count()
}