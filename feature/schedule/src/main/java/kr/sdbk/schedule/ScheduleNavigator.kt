package kr.sdbk.schedule

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kr.sdbk.common.decodeFromStringOrNull
import kr.sdbk.common.encodeToStringOrEmpty
import kr.sdbk.schedule.schedule_detail.ScheduleDetailView

fun NavGraphBuilder.scheduleGraph(
    navController: NavController
) {
    composable<Schedule> {
        ScheduleView(
            navigateToScheduleDetail = {
                val scheduleJson: String = Json.encodeToStringOrEmpty(it)
                navController.navigate(ScheduleDetail(scheduleJson))
            }
        )
    }

    composable<ScheduleDetail> {
        val scheduleJson: String = it.toRoute<ScheduleDetail>().scheduleJson
        val schedule: kr.sdbk.domain.model.schdule.Schedule? = Json.decodeFromStringOrNull(scheduleJson)
        ScheduleDetailView(
            schedule = schedule,
            onBackPressed = { navController.popBackStack() }
        )
    }
}

@Serializable data object Schedule
@Serializable data class ScheduleDetail(val scheduleJson: String)