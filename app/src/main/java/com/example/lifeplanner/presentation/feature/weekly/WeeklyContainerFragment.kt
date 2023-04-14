package com.example.lifeplanner.presentation.feature.weekly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.lifeplanner.R
import com.example.lifeplanner.databinding.FragmentWeeklyContainerBinding
import com.example.lifeplanner.presentation.base.BaseFragment
import com.example.lifeplanner.presentation.feature.main.MainActivity
import com.example.lifeplanner.presentation.feature.setting.SettingFragment
import com.example.lifeplanner.presentation.feature.weekly.week_goal.WeekGoalFragment
import com.example.lifeplanner.presentation.feature.weekly.weeks.WeeksFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeeklyContainerFragment: BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = WeeklyContainerFragment()
    }
    private lateinit var binding: FragmentWeeklyContainerBinding
    override val viewModel: WeeklyContainerViewModel by viewModels()

    private val weeksFragment = WeeksFragment.newInstance()
    private val weekGoalFragment = WeekGoalFragment.newInstance()
    private val settingFragment = SettingFragment.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeeklyContainerBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }

    override fun observeViewModel() {

    }

    private fun initView() {
        binding.bottomNavigation.selectedItemId = R.id.page_day_of_week
        childFragmentManager.beginTransaction().replace(R.id.weekly_container, weeksFragment).commit()
        setNavigationListener()
        setViewEvents()
    }

    private fun setNavigationListener() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_day_of_week -> {
                    childFragmentManager.beginTransaction().replace(R.id.weekly_container, weeksFragment).commit()
                }
                R.id.page_week_goal -> {
                    childFragmentManager.beginTransaction().replace(R.id.weekly_container, weekGoalFragment).commit()
                }
                R.id.page_setting -> {
                    childFragmentManager.beginTransaction().replace(R.id.weekly_container, settingFragment).commit()
                }
                else -> {
                    childFragmentManager.beginTransaction().replace(R.id.weekly_container, weeksFragment).commit()
                }
            }
            true
        }
    }

    private fun setViewEvents() {
        binding.modeText.setOnClickListener {
            (requireActivity() as MainActivity).showChangeModeAlert()
        }
    }
}