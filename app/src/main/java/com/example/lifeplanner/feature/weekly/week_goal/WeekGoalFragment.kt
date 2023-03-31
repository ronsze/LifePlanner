package com.example.lifeplanner.feature.weekly.week_goal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.lifeplanner.base.BaseFragment
import com.example.lifeplanner.databinding.FragmentWeekGoalBinding

class WeekGoalFragment : BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = WeekGoalFragment()
    }
    private lateinit var binding: FragmentWeekGoalBinding
    override val viewModel: WeekGoalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeekGoalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeViewModel() {

    }
}