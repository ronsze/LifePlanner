package kr.sdbk.lifeplanner.features.splash

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import kr.sdbk.lifeplanner.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity<SplashViewModel>() {
    override val viewModel: SplashViewModel by viewModels()

    @Composable
    override fun InitView() {

    }
}