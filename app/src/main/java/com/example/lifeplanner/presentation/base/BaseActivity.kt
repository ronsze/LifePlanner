package com.example.lifeplanner.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    abstract val viewModel: BaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        observeViewModel()
    }

    abstract fun initData()
    abstract fun observeViewModel()
}