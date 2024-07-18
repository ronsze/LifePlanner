package kr.sdbk.lifeplanner

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.sdbk.feature_splash.SplashView

@Composable
fun LifePlannerApp() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainRoute.Home.route
    ) {
        composable(MainRoute.Splash.route) {
            SplashView()
        }

        composable(MainRoute.Home.route) {

        }

        composable(MainRoute.Diary.route) {

        }

        composable(MainRoute.MyPage.route) {

        }
    }
}

sealed class MainRoute(val route: String) {
    data object Splash: MainRoute("splash")
    data object Home: MainRoute("home")
    data object Diary: MainRoute("diary")
    data object MyPage: MainRoute("mypage")
}