package kr.sdbk.feature_splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SplashView(
    viewModel: SplashViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    mainNavController: NavHostController
) {
    Box {
        val viewState by viewModel.viewState.collectAsStateWithLifecycle()
        if (viewState == SplashViewModel.SplashViewState.Loading) viewModel.loadData()
        val scale by animateFloatAsState(
            targetValue = if (viewState == SplashViewModel.SplashViewState.DataLoaded) 5f else 1f,
            animationSpec = tween(
                durationMillis = 800,
                delayMillis = 50
            ),
            finishedListener = { mainNavController.navigate("home") },
            label = "scale"
        )
        Image(
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "",
            modifier = Modifier
                .scale(scale)
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(horizontal = 10.dp)
                .clip(CircleShape)
                .align(Alignment.Center)
        )
    }
}