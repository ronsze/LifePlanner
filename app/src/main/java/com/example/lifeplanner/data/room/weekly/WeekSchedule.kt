package com.example.lifeplanner.data.room.weekly


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class WeekSchedule (
    val name: String,
    val detail: String,
    val dayOfWeekIndex: Int,
    val startTime: String,
    val endTime: String,
): java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}