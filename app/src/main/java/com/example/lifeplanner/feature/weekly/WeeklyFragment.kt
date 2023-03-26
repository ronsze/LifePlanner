package com.example.lifeplanner.feature.weekly

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.lifeplanner.R
import com.example.lifeplanner.base.BaseFragment
import com.example.lifeplanner.databinding.FragmentDailyBinding
import com.example.lifeplanner.databinding.FragmentWeeklyBinding
import com.example.lifeplanner.feature.daily.DailyViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        tabLayout.addOnTabSelectedListener(tabSelectedListener)
    }

    private val tabSelectedListener = object : OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {

        }
        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabReselected(tab: TabLayout.Tab?) {}
    }

    private fun initEachDayFragment() {
        eachDayFragmentList.clear()
        for (i in 0 .. 6) {
            eachDayFragmentList.add(WeeklyEachDayFragment())
        }
    }

    private fun setViewEvents() {
        binding.dayScheduleButton.setOnClickListener {
            val view = binding.dayScheduleLayout

            if (view.visibility == View.GONE) showBottomViewSlide(view)
            else hideBottomViewSlide(view)
        }

        binding.weekGoalButton.setOnClickListener {
            goToWeekGoalPage()
        }

        binding.addScheduleButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                it.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.anim_scale))
                withContext(Dispatchers.Default) { delay(35) }
                goToAddSchedulePage()
            }

        }
    }

    private fun showBottomViewSlide(view: View) {
        lifecycleScope.launch {
            view.visibility = View.VISIBLE

            val param = view.layoutParams
            var height = param.height

            while (height <= binding.root.measuredHeight / 2.5) {
                height += 10
                param.height = height
                view.layoutParams = param
                withContext(Dispatchers.IO) {
                    delay(1)
                }
            }
        }
    }

    private fun hideBottomViewSlide(view: View) {
        lifecycleScope.launch(Dispatchers.Main) {
            val param = view.layoutParams
            var height = param.height

            while (height >= 0) {
                height -= 10
                param.height = height
                view.layoutParams = param
                withContext(Dispatchers.Default) {
                    delay(1)
                }
            }

            view.visibility = View.GONE
        }
    }

    private fun goToWeekGoalPage() {

    }

    private fun goToAddSchedulePage() {

    }
}