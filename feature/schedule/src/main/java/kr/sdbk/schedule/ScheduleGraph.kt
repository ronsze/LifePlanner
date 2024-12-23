package kr.sdbk.schedule

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.scheduleGraph() {
    composable<Schedule> {
        ScheduleView()
    }
}

@Serializable
data object Schedule