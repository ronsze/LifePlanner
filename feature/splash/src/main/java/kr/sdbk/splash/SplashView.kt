package kr.sdbk.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun SplashView(
    navigateToHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        navigateToHome()
    }
}