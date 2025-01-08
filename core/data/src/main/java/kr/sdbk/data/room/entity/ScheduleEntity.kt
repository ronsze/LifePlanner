package kr.sdbk.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.sdbk.domain.model.schdule.AlarmType

@Entity("schedule")
data class ScheduleEntity(
    @PrimaryKey val created: String,
    val title: String,
    val detail: String,
    val hour: Int,
    val minute: Int,
    @ColumnInfo("day_of_week") val dayOfWeek: String,
    val alarmType: String,
    val state: String
)