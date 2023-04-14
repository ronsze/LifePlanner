package com.example.lifeplanner.presentation.feature.weekly.weeks

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.lifeplanner.R
import com.example.lifeplanner.data.room.weekly.WeekSchedule
import com.example.lifeplanner.databinding.FragmentWeeksBinding
import com.example.lifeplanner.presentation.base.BaseFragment
import com.example.lifeplanner.presentation.feature.weekly.WeekEnum
import com.example.lifeplanner.presentation.util.Functions.getDayInWeekIndex
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class WeeksFragment: BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = WeeksFragment()
    }

    private lateinit var binding: FragmentWeeksBinding
    override val viewModel: WeeksViewModel by viewModels()

    private lateinit var adapter: ScheduleAdapter

    private val addScheduleResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == -1) {
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeeksBinding.inflate(inflater, container, false)
        viewModel.loadData()

        return binding.root
    }

    override fun observeViewModel() {
        viewModel.initViewEvent.observe(this) {
            initView()
        }

        viewModel.scheduleListUpdateEvent.observe(this) {
            adapter.differ.submitList(viewModel.getScheduleList(binding.tabLayout.selectedTabPosition))
        }
    }

    private fun initView() {
        adapter = ScheduleAdapter(this::showAddScheduleBottomSheet)
        binding.scheduleRecyclerView.adapter = adapter

        initTabLayout()
        setViewEvents()
    }

    private fun initTabLayout() {
        val tabLayout = binding.tabLayout

        tabLayout.clearOnTabSelectedListeners()
        tabLayout.addOnTabSelectedListener(tabSelectedListener)

        lifecycleScope.launch {
            WeekEnum.values().forEach {
                val tab = tabLayout.newTab()
                tab.text = SpannableString(it.day)
                tabLayout.addTab(tab)
            }
            tabLayout.getTabAt(getDayInWeekIndex())?.select()
        }
    }

    private val tabSelectedListener = object : OnTabSelectedListener {
        override fun onTabSelected(tab: Tab?) {
            tab?.run {
                text?.run {
                    val span = SpannableString(this)
                    span.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.teal_700)),0, length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                    tab.text = span
                }

                adapter.differ.submitList(viewModel.getScheduleList(position))
            }
        }
        override fun onTabUnselected(tab: Tab?) {
            tab?.run {
                text?.run {
                    val span = SpannableString(this)
                    span.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.black)),0, length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                    tab.text = span
                }
            }
        }
        override fun onTabReselected(tab: Tab?) {}
    }

    private fun setViewEvents() {
        binding.addScheduleButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                it.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.anim_scale))
                withContext(Dispatchers.Default) { delay(35) }
                showAddScheduleBottomSheet()
            }
        }
    }

    private fun showAddScheduleBottomSheet(schedule: WeekSchedule? = null) {
        val bottomSheet = AddScheduleBottomSheet(schedule, viewModel)
        bottomSheet.show(parentFragmentManager, bottomSheet.tag)
    }
}