package kr.sdbk.feature_diary

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.feature_diary.databinding.FragmentDiaryBinding

@AndroidEntryPoint
class DiaryFragment: BaseLayoutFragment<FragmentDiaryBinding, DiaryViewModel>(
    FragmentDiaryBinding::inflate
) {
    override val fragmentViewModel: DiaryViewModel by viewModels()

    override fun afterBinding() {

    }

    override fun observeViewModel() {

    }
}