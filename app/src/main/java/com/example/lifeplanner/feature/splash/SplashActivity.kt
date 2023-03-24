package com.example.lifeplanner.feature.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.viewModels
import com.example.lifeplanner.base.BaseActivity
import com.example.lifeplanner.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity() {
    override val viewModel: SplashViewModel by viewModels()
    override fun initData() {
        goToMainAct()
    }

    override fun observeViewModel() {

    }

    private fun goToMainAct() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}