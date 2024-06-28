package kr.sdbk.data.mapper

import com.google.gson.Gson
import kr.sdbk.data.room.entity.ScheduleEntity
import model.schedule.Schedule
import model.schedule.ScheduleState
import model.schedule.Time
import java.time.DayOfWeek

object ScheduleMapper: Mapper<Schedule, ScheduleEntity> {
    override fun Schedule.fromModel(): ScheduleEntity = ScheduleEntity(
        created = created,
        title = title,
        detail = detail,
        time = Gson().toJson(time),
        dayOfWeek = dayOfWeek.name,
        state = state.name
    ).apply {
        this@fromModel.id?.let { this@apply.id = it }
    }

    override fun ScheduleEntity.toModel(): Schedule = Schedule(
        created = created,
        title = title,
        detail = detail,
        time = Gson().fromJson(time, Time::class.java),
        dayOfWeek = DayOfWeek.valueOf(dayOfWeek),
        state = ScheduleState.valueOf(state)
    ).apply {
        this@apply.id = this@toModel.id
    }
}