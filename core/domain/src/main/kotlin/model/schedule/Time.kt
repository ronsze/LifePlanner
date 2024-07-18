package model.schedule

data class Time(
    val hour: Int,
    val minute: Int
) {
    fun toText() = "$hour:$minute"
}