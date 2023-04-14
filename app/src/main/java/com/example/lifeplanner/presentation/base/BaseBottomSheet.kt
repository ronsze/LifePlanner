package com.example.lifeplanner.presentation.base

import androidx.fragment.app.FragmentManager
import com.example.lifeplanner.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseBottomSheet: BottomSheetDialogFragment() {
    private var startTransaction: Boolean = false

    override fun show(manager: FragmentManager, tag: String?) {
        if (!startTransaction) {
            startTransaction = true
            super.show(manager, tag)
        }
    }
}