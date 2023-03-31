package com.example.lifeplanner.feature.daily.add_daily_schedule

import com.example.lifeplanner.base.BaseViewModel
import com.example.lifeplanner.room.WeekSchedule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddDayScheduleViewModel @Inject constructor(

): BaseViewModel() {
    var schedule: WeekSchedule? = null
}