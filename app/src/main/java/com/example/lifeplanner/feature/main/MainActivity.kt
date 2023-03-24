package com.example.lifeplanner.feature.main

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.lifeplanner.R
import com.example.lifeplanner.base.BaseActivity
import com.example.lifeplanner.databinding.ActivityMainBinding
import com.example.lifeplanner.feature.daily.DailyFragment
import com.example.lifeplanner.feature.daily.DailyFragmentDirections
import com.example.lifeplanner.feature.setting.SettingActivity
import com.example.lifeplanner.feature.weekly.WeeklyFragment
import com.example.lifeplanner.feature.weekly.WeeklyFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override val viewModel: MainViewModel by viewModels()

    private lateinit var weeklyFragment: WeeklyFragment
    private lateinit var dailyFragment: DailyFragment

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
        binding.settingButton.setOnClickListener {
            goToSettingAct()
        }

        binding.modeChangeButton.setOnClickListener {
            showChangeModeAlert()
        }
    }

    private fun changeFragment() {
        when (viewModel.mode) {
            MainViewModel.ClockMode.WEEKLY.mode -> changeToWeekly()
            MainViewModel.ClockMode.DAILY.mode -> changeToDaily()
            else -> throw java.lang.Exception()
        }
        binding.modeChangeButton.text = getCurrentModeString()
    }

    private fun changeToWeekly() {
        weeklyFragment = WeeklyFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.backpressed_anim_slide_left, R.anim.backpressed_anim_slide_right)
            .replace(R.id.fragment_container, weeklyFragment)
            .commit()
    }

    private fun changeToDaily() {
        dailyFragment = DailyFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.anim_slide_right, R.anim.anim_slide_left)
            .replace(R.id.fragment_container, dailyFragment)
            .commit()
    }

    private fun showChangeModeAlert() {
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

    private fun goToSettingAct() {
        val intent = Intent(this, SettingActivity::class.java)
        startActivity(intent)
    }
}