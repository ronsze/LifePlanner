package com.example.lifeplanner.feature.weekly.add_week_schedule

import com.example.lifeplanner.base.BaseViewModel
import com.example.lifeplanner.room.WeekSchedule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddWeekScheduleViewModel @Inject constructor(

): BaseViewModel() {
    var schedule: WeekSchedule? = null
}