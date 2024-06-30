package kr.sdbk.core_common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.AnimBuilder
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kr.sdbk.core_common.R

abstract class BaseFragment<B: ViewDataBinding, V: BaseViewModel>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> B
): Fragment() {
    private var _binding: B? = null
    val binding: B get() = _binding!!

    abstract val fragmentViewModel: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterBinding()
        observeViewModel()
    }

    abstract fun afterBinding()
    abstract fun observeViewModel()

    protected fun repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }

    protected fun navigateTo(@IdRes destinationId: Int, args: Bundle = bundleOf(), options: NavOptions = navOptions {}) = findNavController().navigate(destinationId, args, options)
    protected fun navigateTo(deepLinkReq: NavDeepLinkRequest, options: NavOptions = navOptions {}) = findNavController().navigate(deepLinkReq, options)

    protected fun NavOptionsBuilder.getSlideAnim() = anim {
        enter = R.anim.anim_left_in
        exit = R.anim.anim_right_out
        popEnter = R.anim.anim_right_in
        popExit = R.anim.anim_left_out
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}