package com.example.lifeplanner.base

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        observeViewModel()
        super.onCreate(savedInstanceState)
    }

    abstract fun observeViewModel()
}