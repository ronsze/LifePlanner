package com.example.lifeplanner.feature.daily.add_daily_schedule

import android.os.Build.VERSION
import androidx.activity.viewModels
import com.example.lifeplanner.base.BaseActivity
import com.example.lifeplanner.databinding.ActivityAddDailyScheduleBinding
import com.example.lifeplanner.room.WeekSchedule
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddDayScheduleActivity: BaseActivity() {
    private lateinit var binding: ActivityAddDailyScheduleBinding
    override val viewModel: AddDayScheduleViewModel by viewModels()

    override fun initData() {
        binding = ActivityAddDailyScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val schedule = if (VERSION.SDK_INT >= 33) {
            intent.getSerializableExtra("schedule", WeekSchedule::class.java)
        } else {
            intent.getSerializableExtra("schedule") as WeekSchedule
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