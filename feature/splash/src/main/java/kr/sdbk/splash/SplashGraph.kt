package kr.sdbk.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.splashGraph(
    navigateToScheduleView: () -> Unit
) {
    composable<Splash> {
        SplashView(
            navigateToScheduleView = navigateToScheduleView
        )
    }
}

@Serializable
data object Splash