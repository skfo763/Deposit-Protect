package com.skfo763.depositprotect.extension

import androidx.databinding.BindingAdapter
import com.skfo763.base.theme.ThemeType
import com.skfo763.component.navigationmenu.NaviDrawerMenuView
import com.skfo763.depositprotect.R

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

    @JvmStatic
    @BindingAdapter("currentUiTheme")
    fun NaviDrawerMenuView.setUiThemeText(value: ThemeType) {
        this.value = when(value) {
            ThemeType.DEFAULT_MODE -> context.getString(R.string.theme_system_default)
            ThemeType.LIGHT_MODE -> context.getString(R.string.theme_light)
            ThemeType.DARK_MODE -> context.getString(R.string.theme_dark)
        }
    }

}