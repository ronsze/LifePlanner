package kr.sdbk.domain.model.schdule

import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val created: String,
    val title: String,
    val detail: String,
    val hour: Int,
    val minute: Int,
    val dayOfWeek: DayOfWeek,
    var state: ScheduleState = ScheduleState.NOT_YET
)