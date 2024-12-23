package kr.sdbk.lifeplanner

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kr.sdbk.diary.Diary
import kr.sdbk.schedule.Schedule
import kr.sdbk.setting.Setting

enum class HomeDestinations(
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    val route: Any
) {
    SCHEDULE(
        icon = R.drawable.ic_launcher_background,
        label = R.string.app_name,
        route = Schedule
    ),
    DIARY(
        icon = R.drawable.ic_launcher_background,
        label = R.string.app_name,
        route = Diary
    ),
    SETTING(
        icon = R.drawable.ic_launcher_background,
        label = R.string.app_name,
        route = Setting
    )
}