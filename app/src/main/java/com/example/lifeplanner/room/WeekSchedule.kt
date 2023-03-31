package com.example.lifeplanner.room


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class WeekSchedule (
    val name: String,
    val detail: String,
    val dayOfWeek: String,
    val startTime: String,
    val endTime: String,
): java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}