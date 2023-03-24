package com.example.lifeplanner.feature.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.lifeplanner.base.BaseFragment
import com.example.lifeplanner.databinding.FragmentDailyBinding
import com.example.lifeplanner.feature.weekly.WeeklyFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFragment: BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = DailyFragment()
    }

    private lateinit var binding: FragmentDailyBinding
    override val viewModel: DailyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyBinding.inflate(inflater, container, false)
        startLoading()

        viewModel.loadData()

        return binding.root
    }

    override fun observeViewModel() {
        viewModel.initViewEvent.observe(this) {
            finishLoading()
            initView()
        }
    }

    private fun startLoading() {

    }

    private fun finishLoading() {

    }

    private fun initView() {
    }
}