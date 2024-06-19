package kr.sdbk.feature_splash

import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
        repeatOnStated { fragmentViewModel.viewState.collect { observeViewState(it) } }
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
            navigateToHome()
        }
    }

    private fun navigateToHome() {

    }
}