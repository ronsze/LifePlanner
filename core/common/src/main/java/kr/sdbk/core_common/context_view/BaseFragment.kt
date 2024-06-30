package kr.sdbk.core_common.context_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kr.sdbk.core_common.R
import kr.sdbk.core_common.viewmodel.BaseViewModel


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
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterBinding()
        observeViewModel()
        setFragmentResultListeners()
    }

    abstract fun afterBinding()
    abstract fun observeViewModel()
    open fun setFragmentResultListeners() {}

    protected fun onBackPressedListener(action: () -> Unit) {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { action() }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    protected fun repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}