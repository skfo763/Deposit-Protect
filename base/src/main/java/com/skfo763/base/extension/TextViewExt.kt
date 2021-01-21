package com.skfo763.base.extension

import android.view.View
import android.widget.TextView

fun TextView.setTextWithVisibility(textString: String?, visibilityAtNull: Int = View.GONE) {
    textString?.let {
        text = it
    } ?: kotlin.run {
        this.visibility = visibilityAtNull
    }
}