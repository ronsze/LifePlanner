package kr.sdbk.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.splashGraph(
    navigateToHome: () -> Unit
) {
    composable<Splash> {
        SplashView(
            navigateToHome = navigateToHome
        )
    }
}

@Serializable
data object Splash