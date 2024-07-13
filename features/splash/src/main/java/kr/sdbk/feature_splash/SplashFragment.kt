package kr.sdbk.feature_splash

import android.graphics.Paint.Align
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
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.navOptions
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.core_common.context_view.BaseComposeFragment

@AndroidEntryPoint
class SplashFragment: BaseComposeFragment<SplashViewModel>() {
    override val fragmentViewModel: SplashViewModel by viewModels()

    override fun afterComposed() {}

    @Composable
    override fun Root() {
        Box {
            val viewState by fragmentViewModel.viewState.collectAsStateWithLifecycle()
            if (viewState == SplashViewModel.SplashViewState.Loading) fragmentViewModel.loadData()
            val scale by animateFloatAsState(
                targetValue = if (viewState == SplashViewModel.SplashViewState.DataLoaded) 5f else 1f,
                animationSpec = tween(
                    durationMillis = 800,
                    delayMillis = 50
                ),
                finishedListener = { navigateToDiary() },
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

    private fun navigateToDiary() {
        val req = NavDeepLinkRequest.Builder
            .fromUri("android-app://kr.sdbk.lifeplanner/home_nav".toUri())
            .build()

        val option = navOptions {
            getSlideAnim()
            popUpTo(R.id.splashFragment) {
                inclusive = true
            }
        }

        navigateTo(req, option)
    }
}