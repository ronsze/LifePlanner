package kr.sdbk.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.core_common.context_view.BaseFragment
import kr.sdbk.home.databinding.FragmentAddScheduleBinding

@AndroidEntryPoint
class AddScheduleFragment: BaseFragment<FragmentAddScheduleBinding, AddScheduleViewModel>(
    FragmentAddScheduleBinding::inflate
) {
    override val fragmentViewModel: AddScheduleViewModel by viewModels()

    override fun afterBinding() {

    }

    override fun observeViewModel() {

    }
}