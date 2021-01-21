package com.skfo763.depositprotect.extension

import androidx.databinding.BindingAdapter
import com.skfo763.component.navigationmenu.NaviDrawerMenuView

object NaviDrawerMenuExt {

    @JvmStatic
    @BindingAdapter("setOnMenuClickListener")
    fun NaviDrawerMenuView.setOnMenuClickListener(onMenuClick: ((String?) -> Unit)? = null) {
        onMenuClick?.let {
            this.setMenuClickedListener(it)
        }
    }

    @JvmStatic
    @BindingAdapter("menu_value")
    fun NaviDrawerMenuView.setMenuValue(value: String? = null) {
        this.value = value
    }


}