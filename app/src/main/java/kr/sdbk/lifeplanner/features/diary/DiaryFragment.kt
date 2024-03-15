package kr.sdbk.lifeplanner.features.diary

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.lifeplanner.base.BaseFragment

@AndroidEntryPoint
class DiaryFragment: BaseFragment<DiaryViewModel>() {
    override val viewModel: DiaryViewModel by viewModels()

    @Composable
    override fun InitView() {
        Text(text = "dar")
    }
}