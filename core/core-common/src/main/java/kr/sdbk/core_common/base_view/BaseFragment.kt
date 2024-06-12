package kr.sdbk.core_common.base_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

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
}