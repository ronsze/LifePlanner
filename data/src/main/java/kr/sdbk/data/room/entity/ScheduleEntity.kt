package kr.sdbk.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Schedule")
data class ScheduleEntity(
    val created: String,
    val title: String,
    val detail: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}