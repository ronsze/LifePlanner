package kr.sdbk.lifeplanner.features.main

import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.lifeplanner.R
import kr.sdbk.lifeplanner.base.BaseActivity
import kr.sdbk.lifeplanner.features.diary.Diary
import kr.sdbk.lifeplanner.features.schedule.Schedule
import kr.sdbk.lifeplanner.features.setting.Setting

@AndroidEntryPoint
class MainActivity: BaseActivity<MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    @Composable
    override fun InitView() {
        val navController = rememberNavController()
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) },
            ) { innerPadding ->
                NavHost(
                    navController,
                    startDestination = MainMenu.Schedule.route,
                    Modifier.padding(innerPadding)
                ) {
                    composable(MainMenu.Schedule.route) { Schedule() }
                    composable(MainMenu.Diary.route) { Diary() }
                    composable(MainMenu.Setting.route) { Setting() }
                }
            }

        }
    }

    @Composable
    private fun BottomNavigationBar(
        navController: NavController
    ) {
        BottomNavigation {
            NavItem(scope = this, icon = R.drawable.ic_launcher_background, item = MainMenu.Schedule, navController)
            NavItem(scope = this, icon = R.drawable.ic_launcher_foreground, item = MainMenu.Diary, navController)
            NavItem(scope = this, icon = R.drawable.ic_launcher_foreground, item = MainMenu.Setting, navController)
        }
    }

    @Composable
    private fun NavIcon(resourceId: Int) {
        Icon(
            painter = painterResource(id = resourceId),
            contentDescription = "",
            modifier = Modifier.size(75.dp)
        )
    }

    @Composable
    private fun NavItem(
        scope: RowScope,
        @DrawableRes icon: Int,
        item: MainMenu,
        navController: NavController
    ) = scope.run {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        BottomNavigationItem(
            icon = { NavIcon(resourceId = icon) },
            label = { Text(text = stringResource(id = item.label), fontSize = 16.sp) },
            selectedContentColor = Color.Cyan,
            unselectedContentColor = Color.LightGray,
            alwaysShowLabel = true,
            selected = currentRoute == item.route,
            onClick = {
                navController.navigate(item.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }

    sealed class MainMenu(val route: String, @StringRes val label: Int) {
        data object Schedule: MainMenu("schedule", R.string.title_schedule)
        data object Diary: MainMenu("diary", R.string.title_diary)
        data object Setting: MainMenu("setting", R.string.title_setting)
    }

    @Preview
    @Composable
    fun NavItemPreview() {
        Row {
            NavItem(scope = this, icon = R.drawable.ic_launcher_foreground, item = MainMenu.Diary, rememberNavController())
        }
    }
}