package com.example.lifeplanner.feature.weekly.weeks

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lifeplanner.R
import com.example.lifeplanner.data.SimpleTime
import com.example.lifeplanner.databinding.ItemDayScheduleBinding
import com.example.lifeplanner.room.WeekSchedule

class DayScheduleAdapter(
    private val scheduleList: ArrayList<WeekSchedule>,
    private val onClickItem: (WeekSchedule) -> Unit
) : RecyclerView.Adapter<DayScheduleViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayScheduleViewHolder {
        val binding =
            ItemDayScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        context = parent.context

        return DayScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayScheduleViewHolder, position: Int) {
        val item = scheduleList[position]

        holder.binding.run {
            nameText.text = item.name
            detailText.text = item.detail

            val startTime = stringToSimpleDate(item.startTime)
            val endTime = stringToSimpleDate(item.endTime)
            val timeDiff = getTimeDiff(startTime, endTime)
            timeText.text = String.format(
                context.getString(R.string.time_range_format),
                startTime.hour,
                startTime.min,
                endTime.hour,
                endTime.min,
                timeDiff.hour,
                timeDiff.min
            )

            if (position == scheduleList.lastIndex) divider.visibility = View.INVISIBLE
            else divider.visibility = View.VISIBLE

            root.setOnClickListener {
                onClickItem(item)
            }
        }
    }

    private fun getTimeDiff(start: SimpleTime, end: SimpleTime): SimpleTime {
        var hour = end.hour - start.hour
        var min = end.min - start.min
        if (min < 0) {
            hour -= 1
            min += 60
        }

        return SimpleTime(hour, min)
    }

    override fun getItemCount(): Int = scheduleList.count()

    fun updateList(list: List<WeekSchedule>) {
        scheduleList.clear()
        scheduleList.addAll(list)
        notifyDataSetChanged()
    }

    private fun stringToSimpleDate(str: String): SimpleTime {
        val arr = str.split(":")
        return SimpleTime(arr[0].toInt(), arr[1].toInt())
    }
}