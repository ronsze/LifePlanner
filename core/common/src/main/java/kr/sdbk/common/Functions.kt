package kr.sdbk.common

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

inline fun <reified T> Json.encodeToStringOrEmpty(value: T?): String = if (value == null) "" else Json.encodeToString(value)
inline fun <reified T> Json.decodeFromStringOrNull(value: String): T? = if (value == "") null else Json.decodeFromString<T>(value)

object TimeFunctions {
    private val defaultDateFormat = "yyyy-MM-dd"
    private const val DAY_MILLIS = 86400000

    fun getToday() = convertMillisToDate(System.currentTimeMillis())

    fun convertMillisToDate(millis: Long): String {
        val sdf = SimpleDateFormat(defaultDateFormat, Locale.getDefault())
        return sdf.format(Date(millis))
    }

    fun convertDateToMillis(dateString: String): Long {
        val sdf = SimpleDateFormat(defaultDateFormat, Locale.getDefault())
        val date = sdf.parse(dateString)
        return date!!.time
    }

    fun getNextDay(dateString: String) = convertMillisToDate(convertDateToMillis(dateString) + DAY_MILLIS)
    fun getPreviousDay(dateString: String) = convertMillisToDate(convertDateToMillis(dateString) - DAY_MILLIS)
}