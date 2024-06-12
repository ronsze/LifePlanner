package kr.sdbk.feature_splash

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.core_common.base.BaseFragment
import kr.sdbk.feature_splash.databinding.FragmentSplashBinding

@AndroidEntryPoint
class SplashFragment: BaseFragment<FragmentSplashBinding, SplashViewModel>(
    FragmentSplashBinding::inflate
) {
    override val fragmentViewModel: SplashViewModel by viewModels()

    override fun afterBinding() {

    }

    override fun observeViewModel() {

    }
}