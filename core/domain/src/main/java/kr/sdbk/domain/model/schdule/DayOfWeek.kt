package kr.sdbk.domain.model.schdule

import java.util.Calendar

enum class DayOfWeek {
    SUN, MON, TUE, WED, THU, FRI, SAT;

    companion object {
        fun getCurrentDayOfWeek(): DayOfWeek {
            val calendar = Calendar.getInstance()
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

            return when (dayOfWeek) {
                1 -> SUN
                2 -> MON
                3 -> TUE
                4 -> WED
                5 -> THU
                6 -> FRI
                7 -> SAT
                else -> throw Exception("DayOfWeek not matched")
            }
        }
    }
}