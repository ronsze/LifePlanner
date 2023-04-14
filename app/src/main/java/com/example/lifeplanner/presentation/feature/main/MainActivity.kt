package com.example.lifeplanner.presentation.feature.main

import android.app.AlertDialog
import androidx.activity.viewModels
import com.example.lifeplanner.R
import com.example.lifeplanner.databinding.ActivityMainBinding
import com.example.lifeplanner.presentation.base.BaseActivity
import com.example.lifeplanner.presentation.feature.daily.DailyContainerFragment
import com.example.lifeplanner.presentation.feature.weekly.WeeklyContainerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override val viewModel: MainViewModel by viewModels()

    private lateinit var weeklyFragment: WeeklyContainerFragment
    private lateinit var dailyFragment: DailyContainerFragment

    override fun initData() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadData()
    }

    override fun observeViewModel() {
        viewModel.initViewEvent.observe(this) {
            initView()
        }
    }

    private fun initView() {
        setViewEvents()
        changeFragment()
    }

    private fun getCurrentModeString() =
        when (viewModel.mode) {
            MainViewModel.ClockMode.WEEKLY.mode -> "Weekly"
            MainViewModel.ClockMode.DAILY.mode -> "Daily"
            else -> throw java.lang.Exception()
        }

    private fun setViewEvents() {

    }

    private fun changeFragment() {
        when (viewModel.mode) {
            MainViewModel.ClockMode.WEEKLY.mode -> changeToWeekly()
            MainViewModel.ClockMode.DAILY.mode -> changeToDaily()
            else -> throw java.lang.Exception()
        }
    }

    private fun changeToWeekly() {
        weeklyFragment = WeeklyContainerFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.anim_scale_up, R.anim.anim_scale_down)
            .replace(R.id.fragment_container, weeklyFragment)
            .commit()
    }

    private fun changeToDaily() {
        dailyFragment = DailyContainerFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.anim_scale_up, R.anim.anim_scale_down)
            .replace(R.id.fragment_container, dailyFragment)
            .commit()
    }

    fun showChangeModeAlert() {
        AlertDialog.Builder(this)
            .setTitle("mode change?")
            .setMessage("sure?")
            .setPositiveButton("yes") { _, _, ->
                viewModel.changeMode()
                changeFragment()
            }
            .setNegativeButton("no") { _, _, -> }
            .create().show()
    }
}