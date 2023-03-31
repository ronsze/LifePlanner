package com.example.lifeplanner.feature.weekly.add_week_schedule

import android.os.Build.VERSION
import androidx.activity.viewModels
import com.example.lifeplanner.base.BaseActivity
import com.example.lifeplanner.databinding.ActivityAddWeekScheduleBinding
import com.example.lifeplanner.room.WeekSchedule
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWeekScheduleActivity : BaseActivity() {
    private lateinit var binding: ActivityAddWeekScheduleBinding
    override val viewModel: AddWeekScheduleViewModel by viewModels()

    override fun initData() {
        binding = ActivityAddWeekScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val schedule = if (VERSION.SDK_INT >= 33) {
            intent.getSerializableExtra("schedule", WeekSchedule::class.java)
        } else {
            intent.getSerializableExtra("schedule") as? WeekSchedule
        }

        viewModel.schedule = schedule
        initView()
    }

    override fun observeViewModel() {

    }

    private fun initView() {
        setViewEvents()
    }

    private fun setViewEvents() {

    }
}