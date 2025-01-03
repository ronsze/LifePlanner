package kr.sdbk.domain.model.schdule

import java.util.Calendar

enum class DayOfWeek {
    SUN, MON, TUE, WED, THU, FRI, SAT;

    companion object {
        fun getCurrentDayOfWeek(): DayOfWeek {
            val calendar = Calendar.getInstance()
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

            return DayOfWeek.entries[dayOfWeek]
        }
    }
}