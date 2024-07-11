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


abstract class BaseLayoutFragment<B: ViewDataBinding, V: BaseViewModel>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> B
): BaseFragment<V>() {
    private var _binding: B? = null
    val binding: B get() = _binding!!

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
    }

    abstract fun afterBinding()
    abstract fun observeViewModel()

    protected fun repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}