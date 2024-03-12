package kr.sdbk.lifeplanner.features.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.lifeplanner.base.BaseActivity
import kr.sdbk.lifeplanner.features.main.MainActivity

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity<SplashViewModel>() {
    override val viewModel: SplashViewModel by viewModels()

    @Composable
    override fun InitView() {
        val viewState by remember { viewModel.viewState }
        SplashView()
        handleViewState(viewState)
    }

    @Composable
    private fun SplashView() {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = "",
                modifier = Modifier
                    .size(350.dp)
                    .align(Alignment.Center)
            )
        }
    }

    private fun handleViewState(state: SplashViewModel.SplashState) {
        when (state) {
            SplashViewModel.SplashState.Nothing -> Unit
            SplashViewModel.SplashState.Loaded -> navToMain()
        }
    }

    private fun navToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK
        startActivitySlide(intent)
        finish()
    }
}