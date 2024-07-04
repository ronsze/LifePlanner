package kr.sdbk.core_common.context_view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import kr.sdbk.core_common.R

abstract class BaseFragment<V>: Fragment() {
    abstract val fragmentViewModel: V

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListeners()
    }
    open fun setFragmentResultListeners() {}

    protected fun onBackPressedListener(action: () -> Unit) {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { action() }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    protected fun navigateTo(direction: NavDirections) = findNavController().navigate(direction)
    protected fun navigateTo(@IdRes destinationId: Int) = findNavController().navigate(destinationId)
    protected fun navigateTo(deepLinkReq: NavDeepLinkRequest, option: NavOptions = navOptions{}) = findNavController().navigate(deepLinkReq, option)
    protected fun popBackStack() = findNavController().popBackStack()

    protected fun NavOptionsBuilder.getSlideAnim() {
        anim {
            enter = R.anim.from_right
            exit = R.anim.to_left
            popEnter = R.anim.from_left
            popExit = R.anim.to_right
        }
    }
}