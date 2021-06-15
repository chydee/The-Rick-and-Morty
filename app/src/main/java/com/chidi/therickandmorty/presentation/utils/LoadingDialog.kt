package com.chidi.therickandmorty.presentation.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.chidi.therickandmorty.R

class LoadingDialog(private val activity: Activity) {

    private var dialog: AlertDialog? = null

    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.progress_layout, null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog?.show()
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

}