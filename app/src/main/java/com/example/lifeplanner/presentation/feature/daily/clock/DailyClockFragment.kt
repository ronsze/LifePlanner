package com.example.lifeplanner.presentation.feature.daily.clock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.lifeplanner.presentation.base.BaseFragment
import com.example.lifeplanner.databinding.FragmentDailyClockBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyClockFragment: BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = DailyClockFragment()
    }

    private lateinit var binding: FragmentDailyClockBinding
    override val viewModel: DailyClockViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyClockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeViewModel() {

    }
}