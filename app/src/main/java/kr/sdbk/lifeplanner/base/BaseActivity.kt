package kr.sdbk.lifeplanner.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import kr.sdbk.lifeplanner.ui.theme.LifePlannerTheme

abstract class BaseActivity<out T: BaseViewModel>: ComponentActivity() {
    abstract val viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifePlannerTheme {
                InitView()
            }
        }
    }

    @Composable
    abstract fun InitView()
}