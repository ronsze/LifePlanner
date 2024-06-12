package kr.sdbk.core_common.base_view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B: ViewDataBinding, V: BaseViewModel>(
    private val inflate: (LayoutInflater) -> B
): AppCompatActivity() {
    private var _binding: B? = null
    val binding: B get() = _binding!!

    abstract val activityViewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate(layoutInflater)
        setContentView(binding.root)

        afterBinding()
        observeViewModel()
    }

    abstract fun afterBinding()
    abstract fun observeViewModel()
}