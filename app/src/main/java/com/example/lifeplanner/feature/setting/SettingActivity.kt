package com.example.lifeplanner.feature.setting

import androidx.activity.viewModels
import com.example.lifeplanner.base.BaseActivity
import com.example.lifeplanner.databinding.ActivitySettingBinding

class SettingActivity : BaseActivity() {
    private lateinit var binding: ActivitySettingBinding
    override val viewModel: SettingViewModel by viewModels()

    override fun initData() {
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observeViewModel() {

    }
}