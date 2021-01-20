package com.skfo763.depositprotect.extension

import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter

object EditTextExt {

    @JvmStatic
    @BindingAdapter("completeKeyListener")
    fun EditText.setCompleteKeyListener(onKeyEvent: ((View, Int, KeyEvent?) -> Boolean)? = null) {
        onKeyEvent?.let {
            setOnKeyListener(it)
        }
    }

}