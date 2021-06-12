package com.chidi.therickandmorty.presentation.utils.extensions

import android.view.View

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {

    this.visibility = View.GONE
}