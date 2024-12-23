package kr.sdbk.lifeplanner

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import kr.sdbk.diary.diaryGraph
import kr.sdbk.schedule.Schedule
import kr.sdbk.schedule.scheduleGraph
import kr.sdbk.setting.settingGraph
import kr.sdbk.splash.Splash
import kr.sdbk.splash.splashGraph

@Composable
fun LifePlannerApp(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Splash
    ) {
        splashGraph(
            navigateToHome = {
                navHostController.navigate(Home)
            }
        )

        composable<Home> {
            Home()
        }
    }
}

@Composable
private fun Home(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                HomeDestinations.entries.forEach {
                    NavigationBarItem(
                        icon = {
                            Image(
                                painterResource(it.icon),
                                contentDescription = ""
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(it.label)
                            )
                        },
                        selected = false,
                        onClick = { navController.navigate(it.route) },
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Schedule,
            modifier = Modifier.padding(padding)
        ) {
            scheduleGraph()
            diaryGraph()
            settingGraph()
        }
    }
}

@Serializable data object Home