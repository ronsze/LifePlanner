package com.example.lifeplanner.feature.weekly.week_goal

import androidx.activity.viewModels
import com.example.lifeplanner.base.BaseActivity
import com.example.lifeplanner.databinding.ActivityAddWeekGoalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWeekGaolActivity : BaseActivity() {
    private lateinit var binding: ActivityAddWeekGoalBinding
    override val viewModel: AddWeekGoalViewModel by viewModels()

    override fun initData() {
        binding = ActivityAddWeekGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observeViewModel() {

    }
}