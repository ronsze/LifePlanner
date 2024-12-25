package kr.sdbk.domain.model.schdule

data class Schedule(
    val created: String,
    val name: String,
    val detail: String,
    val hour: Int,
    val minute: Int,
    val dayOfWeek: DayOfWeek,
    val state: ScheduleState
)