package kr.sdbk.core_common.context_view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import kr.sdbk.core_common.viewmodel.BaseViewModel

abstract class BaseComposeFragment<V: BaseViewModel>: BaseFragment<V>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent { Root() }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterComposed()
    }

    abstract fun afterComposed()

    @Composable
    abstract fun Root()

    override fun onDestroyView() {
        findNavController().saveState()
        super.onDestroyView()
    }
}