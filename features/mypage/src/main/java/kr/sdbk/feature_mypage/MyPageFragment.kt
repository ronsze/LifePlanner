package kr.sdbk.feature_mypage

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.core_common.context_view.BaseLayoutFragment
import kr.sdbk.feature_mypage.databinding.FragmentMypageBinding

@AndroidEntryPoint
class MyPageFragment: BaseLayoutFragment<FragmentMypageBinding, MyPageViewModel>(
    FragmentMypageBinding::inflate
) {
    override val fragmentViewModel: MyPageViewModel by viewModels()

    override fun afterBinding() {

    }

    override fun observeViewModel() {

    }
}