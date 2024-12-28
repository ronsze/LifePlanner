package kr.sdbk.data.mapper

import kr.sdbk.data.room.entity.ScheduleEntity
import kr.sdbk.domain.model.schdule.DayOfWeek
import kr.sdbk.domain.model.schdule.Schedule
import kr.sdbk.domain.model.schdule.ScheduleState

object ScheduleMapper {
    fun Schedule.toEntity() = ScheduleEntity(
        created = created,
        title = title,
        detail = detail,
        hour = hour,
        minute = minute,
        dayOfWeek = dayOfWeek.name,
        state = state.name,
    )

    fun ScheduleEntity.toData() = Schedule(
        created = created,
        title = title,
        detail = detail,
        hour = hour,
        minute = minute,
        dayOfWeek = DayOfWeek.valueOf(dayOfWeek),
        state = ScheduleState.valueOf(state),
    )
}