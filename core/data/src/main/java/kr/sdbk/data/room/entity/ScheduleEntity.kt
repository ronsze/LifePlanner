package kr.sdbk.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Schedule")
data class ScheduleEntity(
    val created: String,
    val title: String,
    val detail: String,
    val time: String,
    val dayOfWeek: String,
    val state: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}