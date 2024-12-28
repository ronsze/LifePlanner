package kr.sdbk.domain.model.schdule

data class Schedule(
    val created: String,
    val title: String,
    val detail: String,
    val hour: Int,
    val minute: Int,
    val dayOfWeek: DayOfWeek,
    val state: ScheduleState
)