package kr.sdbk.lifeplanner.features.main

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.lifeplanner.base.BaseActivity

@AndroidEntryPoint
class MainActivity: BaseActivity<MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    @Composable
    override fun InitView() {

    }
}