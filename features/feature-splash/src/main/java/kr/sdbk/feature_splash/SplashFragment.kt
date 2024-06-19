package kr.sdbk.feature_splash

import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
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
        repeatOnStarted { fragmentViewModel.viewState.collect { observeViewState(it) } }
    }

    private fun observeViewState(state: SplashViewModel.SplashViewState) {
        when (state) {
            SplashViewModel.SplashViewState.Loading -> fragmentViewModel.loadData()
            SplashViewModel.SplashViewState.DataLoaded -> startLogoAnimation()
        }
    }

    private fun startLogoAnimation() = binding.icLogo.run {
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_logo_scale).apply {
            setAnimationListener(logoAnimationListener)
        }
        startAnimation(anim)
    }

    private val logoAnimationListener = object : AnimationListener {
        override fun onAnimationStart(animation: Animation?) {}
        override fun onAnimationRepeat(animation: Animation?) {}
        override fun onAnimationEnd(animation: Animation?) {
            navigateToDiary()
        }
    }

    private fun navigateToDiary() {
        val req = NavDeepLinkRequest.Builder
            .fromUri("android-app://kr.sdbk.lifePlanner/diary_nav".toUri())
            .build()
        navigateTo(req)
    }
}