package kr.sdbk.domain.model.schdule

import java.util.Calendar

enum class DayOfWeek {
    SUN, MON, TUE, WED, THU, FRI, SAT;

    companion object {
        fun getCurrentDayOfWeek(): DayOfWeek {
            val calendar = Calendar.getInstance()
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

            return when (dayOfWeek) {
                0 -> SUN
                1 -> MON
                2 -> TUE
                3 -> WED
                4 -> THU
                5 -> FRI
                6 -> SAT
                else -> throw Exception("DayOfWeek not matched")
            }
        }
    }
}