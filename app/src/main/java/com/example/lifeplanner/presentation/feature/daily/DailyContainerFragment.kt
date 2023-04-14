package com.example.lifeplanner.presentation.feature.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.lifeplanner.R
import com.example.lifeplanner.databinding.FragmentDailyBinding
import com.example.lifeplanner.presentation.base.BaseFragment
import com.example.lifeplanner.presentation.feature.daily.clock.DailyClockFragment
import com.example.lifeplanner.presentation.feature.daily.diary.DailyDiaryFragment
import com.example.lifeplanner.presentation.feature.main.MainActivity
import com.example.lifeplanner.presentation.feature.setting.SettingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyContainerFragment: BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = DailyContainerFragment()
    }

    private lateinit var binding: FragmentDailyBinding
    override val viewModel: DailyContainerViewModel by viewModels()

    private val dailyClockFragment = DailyClockFragment.newInstance()
    private val dailyDiaryFragment = DailyDiaryFragment.newInstance()
    private val settingFragment = SettingFragment.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }

    override fun observeViewModel() {

    }


    private fun initView() {
        childFragmentManager.beginTransaction().replace(R.id.weekly_container, dailyClockFragment).commit()
        setNavigationListener()
        setViewEvents()
    }

    private fun setNavigationListener() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_week_goal -> {
                    childFragmentManager.beginTransaction().replace(R.id.weekly_container, dailyClockFragment).commit()
                }
                R.id.page_day_of_week -> {
                    childFragmentManager.beginTransaction().replace(R.id.weekly_container, dailyDiaryFragment).commit()
                }
                R.id.page_setting -> {
                    childFragmentManager.beginTransaction().replace(R.id.weekly_container, settingFragment).commit()
                }
                else -> {
                    childFragmentManager.beginTransaction().replace(R.id.weekly_container, dailyClockFragment).commit()
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