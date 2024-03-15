package kr.sdbk.lifeplanner.features.setting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.lifeplanner.base.BaseFragment

@AndroidEntryPoint
class SettingFragment: BaseFragment<SettingViewModel>() {
    override val viewModel: SettingViewModel by viewModels()

    @Composable
    override fun InitView() {
        Text(text = "sett")
    }
}