package com.skfo763.depositprotect.extension

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import com.skfo763.component.underbartext.UnderBarInputTextView

object UnderBarTextInputExt {

    @JvmStatic
    @BindingAdapter("onTextInputClicked")
    fun UnderBarInputTextView.setOnClick(onClick: ((View) -> Unit)? = null) {
        this.onClickEvent = onClick
    }

    @JvmStatic
    @BindingAdapter("highlightText")
    fun UnderBarInputTextView.setHighlightText(text: String? = null) {
        text?.let {
            this.text = text
        }
    }

}