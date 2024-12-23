package kr.sdbk.lifeplanner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kr.sdbk.schedule.Schedule
import kr.sdbk.splash.Splash
import kr.sdbk.splash.splashGraph

@Composable
fun LifePlannerApp(
    modifier: Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Splash,
        modifier = modifier
    ) {
        splashGraph(
            navigateToScheduleView = {
                navHostController.navigate(Schedule)
            }
        )
    }
}