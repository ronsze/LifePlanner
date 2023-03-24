package com.example.lifeplanner.feature.weekly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.lifeplanner.base.BaseFragment
import com.example.lifeplanner.databinding.FragmentDailyBinding
import com.example.lifeplanner.databinding.FragmentWeeklyBinding
import com.example.lifeplanner.feature.daily.DailyViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeeklyFragment: BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = WeeklyFragment()
    }

    private lateinit var binding: FragmentWeeklyBinding
    override val viewModel: WeeklyViewModel by viewModels()

    private val eachDayFragmentList = ArrayList<WeeklyEachDayFragment>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeeklyBinding.inflate(inflater, container, false)
        viewModel.loadData()

        return binding.root
    }

    override fun observeViewModel() {
        viewModel.initViewEvent.observe(this) {
            initView()
        }
    }

    private fun initView() {
        initTabLayout()
        setViewEvents()
    }

    private fun initTabLayout() {
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        tabLayout.clearOnTabSelectedListeners()
        initEachDayFragment()

        viewPager.apply {
            adapter = EachDayViewPagerAdapter(eachDayFragmentList, this@WeeklyFragment)
            isUserInputEnabled = false
            offscreenPageLimit = eachDayFragmentList.count() - 1
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = WeekEnum.values()[position].day
        }.attach()
    }

    private fun initEachDayFragment() {
        eachDayFragmentList.clear()
        for (i in 0 .. 6) {
            eachDayFragmentList.add(WeeklyEachDayFragment())
        }
    }

    private fun setViewEvents() {
        binding.dayScheduleButton.setOnClickListener {

        }

        binding.weekGoalButton.setOnClickListener {

        }

        binding.addScheduleButton.setOnClickListener {

        }
    }
}