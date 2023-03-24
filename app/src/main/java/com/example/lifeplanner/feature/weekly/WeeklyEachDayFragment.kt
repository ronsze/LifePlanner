package com.example.lifeplanner.feature.weekly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifeplanner.databinding.FragmentWeeklyEachDayBinding

class WeeklyEachDayFragment : Fragment() {
    private lateinit var binding: FragmentWeeklyEachDayBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeeklyEachDayBinding.inflate(inflater, container, false)

        return binding.root
    }
}