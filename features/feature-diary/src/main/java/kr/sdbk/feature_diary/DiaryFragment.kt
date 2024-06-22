package kr.sdbk.feature_diary

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.core_common.context_view.BaseFragment
import kr.sdbk.feature_diary.databinding.FragmentDiaryBinding

@AndroidEntryPoint
class DiaryFragment: BaseFragment<FragmentDiaryBinding, DiaryViewModel>(
    FragmentDiaryBinding::inflate
) {
    override val fragmentViewModel: DiaryViewModel by viewModels()

    override fun afterBinding() {

    }

    override fun observeViewModel() {

    }
}