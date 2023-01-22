package com.example.localstorage.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


object ApplicationConstant {
    const val MILLIS = 1000
}

fun View.showKeyboard() {
    this.postDelayed({
        this.isFocusableInTouchMode = true
        this.isFocusable = true
        this.requestFocus()
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }, (ApplicationConstant.MILLIS / 5).toLong())
}

