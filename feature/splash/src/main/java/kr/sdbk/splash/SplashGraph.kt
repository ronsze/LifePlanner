package kr.sdbk.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.splashGraph() {
    composable<Splash> {
        SplashView()
    }
}

@Serializable
data object Splash