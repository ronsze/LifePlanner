package com.example.lifeplanner.presentation.feature.daily.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.lifeplanner.presentation.base.BaseFragment
import com.example.lifeplanner.databinding.FragmentDailyDiaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyDiaryFragment: BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = DailyDiaryFragment()
    }

    private lateinit var binding: FragmentDailyDiaryBinding
    override val viewModel: DailyDiaryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyDiaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeViewModel() {

    }
}