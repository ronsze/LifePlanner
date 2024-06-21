package kr.sdbk.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.core_common.base.BaseFragment
import kr.sdbk.home.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
){
    override val fragmentViewModel: HomeViewModel by viewModels()

    override fun afterBinding() {

    }

    override fun observeViewModel() {

    }
}