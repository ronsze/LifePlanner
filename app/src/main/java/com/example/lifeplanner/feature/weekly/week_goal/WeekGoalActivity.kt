package com.example.lifeplanner.feature.weekly.week_goal

import androidx.activity.viewModels
import com.example.lifeplanner.base.BaseActivity
import com.example.lifeplanner.databinding.ActivityWeekGoalBinding

class WeekGoalActivity : BaseActivity() {
    private lateinit var binding: ActivityWeekGoalBinding
    override val viewModel: WeekGoalViewModel by viewModels()

    override fun initData() {
        binding = ActivityWeekGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observeViewModel() {

    }
}