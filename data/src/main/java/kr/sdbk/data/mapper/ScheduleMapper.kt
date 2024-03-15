package kr.sdbk.data.mapper

import kr.sdbk.data.room.entity.ScheduleEntity
import kr.sdbk.domain.model.schedule.Schedule

object ScheduleMapper {
    fun Schedule.toEntity() = ScheduleEntity(created, title, detail).apply { this@apply.id = this@toEntity.id }
    fun ScheduleEntity.toData() = Schedule(created, id, title, detail)
}