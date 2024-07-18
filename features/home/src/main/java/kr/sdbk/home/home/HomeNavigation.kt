package kr.sdbk.home.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeGraph.Home.route
    ) {
        composable(HomeGraph.Home.route) {
            HomeView(mainNavController = )
        }
    }
}

sealed class HomeGraph(val route: String) {
    data object Home: HomeGraph("home")
    data object AddSchedule: HomeGraph("addSchedule")
}