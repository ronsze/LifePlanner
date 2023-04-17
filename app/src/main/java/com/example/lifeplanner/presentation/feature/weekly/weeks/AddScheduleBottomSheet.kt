package com.example.lifeplanner.presentation.feature.weekly.weeks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.lifeplanner.R
import com.example.lifeplanner.data.room.weekly.WeekSchedule
import com.example.lifeplanner.databinding.FragmentAddScheduleBottomSheetBinding
import com.example.lifeplanner.presentation.base.BaseBottomSheet
import com.example.lifeplanner.presentation.util.Functions.getDayInWeekIndex
import java.util.*

class AddScheduleBottomSheet(
    private val schedule: WeekSchedule?,
    private val viewModel: WeeksViewModel
) : BaseBottomSheet() {
    private lateinit var binding: FragmentAddScheduleBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddScheduleBottomSheetBinding.inflate(inflater, container, false)
        isCancelable = true

        schedule?.run { inputData(this) }
        initView()
        setViewEvents()
        return binding.root
    }

    private fun inputData(schedule: WeekSchedule) {
        binding.nameEdit.setText(schedule.name)
        binding.startTime.text = schedule.startTime
        binding.endTime.text = schedule.endTime
    }

    private fun initView() {
        setSpinner()
    }

    private fun setViewEvents() {
        binding.addButton.setOnClickListener {
            val newSchedule = getSchedule()
            if (schedule == null) {
                viewModel.addSchedule(newSchedule)
            } else {
                newSchedule.id = schedule.id
                viewModel.updateSchedule(newSchedule)
            }
            dismiss()
        }
    }

    private fun setSpinner() {
        val items = resources.getStringArray(R.array.weeks_array)
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item_weeks,
            items
        )
        binding.weekSpinner.adapter = adapter
        binding.weekSpinner.setSelection(getDayInWeekIndex())
    }

    private fun getSchedule(): WeekSchedule {
        with(binding) {
            return WeekSchedule(
                nameEdit.text.toString(),
                "",
                weekSpinner.selectedItemPosition,
                startTime.text.toString(),
                endTime.text.toString()
            )
        }
    }
}