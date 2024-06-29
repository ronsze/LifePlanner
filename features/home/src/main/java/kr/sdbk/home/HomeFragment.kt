package kr.sdbk.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.core_common.context_view.BaseFragment
import kr.sdbk.home.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val fragmentViewModel: HomeViewModel by viewModels()

    override fun afterBinding() = binding.run {
        scheduleRecyclerview.setContent { ScheduleList(fragmentViewModel) }
    }

    override fun observeViewModel() {
        fragmentViewModel.loadData()
    }
}