package com.chidi.therickandmorty.presentation.utils

import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.chidi.therickandmorty.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.showProgressDialog(): AlertDialog {
    val customAlertDialogView = LayoutInflater.from(this.requireContext())
        .inflate(R.layout.progress_layout, null, false)

    return MaterialAlertDialogBuilder(this.requireActivity())
        .setView(customAlertDialogView)
        .setCancelable(false)
        .create()
}