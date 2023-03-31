package com.example.lifeplanner.feature.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.lifeplanner.base.BaseFragment
import com.example.lifeplanner.databinding.FragmentSettingBinding

class SettingFragment : BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = SettingFragment()
    }
    private lateinit var binding: FragmentSettingBinding
    override val viewModel: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun observeViewModel() {

    }
}