package kr.sdbk.data.mapper

import androidx.room.TypeConverter
import com.google.gson.Gson
import kr.sdbk.data.room.entity.ScheduleEntity
import model.schedule.Schedule
import model.schedule.ScheduleState
import model.schedule.Time
import java.time.DayOfWeek

class ScheduleConverter {
    @TypeConverter
    fun ScheduleEntity.toData() = Schedule(
        id = id,
        created = created,
        title = title,
        detail = detail,
        time = Gson().fromJson(time, Time::class.java),
        dayOfWeek = DayOfWeek.valueOf(dayOfWeek),
        state = ScheduleState.valueOf(state)
    )

    @TypeConverter
    fun Schedule.toEntity() = ScheduleEntity(
        created = created,
        title = title,
        detail = detail,
        time = Gson().toJson(time),
        dayOfWeek = dayOfWeek.name,
        state = state.name
    ).apply {
        this@apply.id = this@toEntity.id
    }
}