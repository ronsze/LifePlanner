package model.schedule

import java.time.DayOfWeek

data class Schedule(
    val id: Int,
    val created: String,
    val title: String,
    val detail: String,
    val time: Time,
    val dayOfWeek: DayOfWeek,
    val state: ScheduleState
)