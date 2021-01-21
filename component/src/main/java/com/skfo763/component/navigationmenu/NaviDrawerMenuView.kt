package com.skfo763.component.navigationmenu

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.skfo763.base.extension.dp
import com.skfo763.base.extension.logException
import com.skfo763.component.R
import com.skfo763.component.databinding.ViewNavigationMenuBinding
import java.lang.Exception

class NaviDrawerMenuView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val binding = ViewNavigationMenuBinding.inflate(LayoutInflater.from(context), this)

    @DrawableRes var titleIconRes: Int = R.drawable.ic_baseline_android_24
        set(value) {
            binding.navigationTitleIcon.isVisible = value != R.drawable.ic_baseline_android_24
            binding.navigationTitleIcon.setImageResource(value)
            field = value
        }

    var title: String? = null
        set(value) {
            binding.navigationTitle.text = value ?: ""
            field = value
        }

    @DrawableRes var valueIconRes: Int = R.drawable.ic_baseline_android_24
        set(value) {
            binding.navigationValueIcon.isVisible = value != R.drawable.ic_baseline_android_24
            binding.navigationValueIcon.setImageResource(value)
            field = value
        }

    var value: String? = null
        set(value) {
            binding.navigationValue.text = value ?: ""
            field = value
        }

    init {
        this.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        this.setPadding(context.dp(16))
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)
            this.foreground = ContextCompat.getDrawable(context, typedValue.resourceId)
        }
        getAttrs(context, attributeSet, defStyleAttr)
    }

    private fun getAttrs(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) {
        val attrs = context.theme.obtainStyledAttributes(attributeSet, R.styleable.NaviDrawerMenuView, defStyleAttr, 0)
        try {
            titleIconRes = attrs.getResourceId(R.styleable.NaviDrawerMenuView_menu_title_icon, R.drawable.ic_baseline_android_24)
            title = attrs.getString(R.styleable.NaviDrawerMenuView_menu_title)
            valueIconRes = attrs.getResourceId(R.styleable.NaviDrawerMenuView_menu_value_icon, R.drawable.ic_baseline_android_24)
            value = attrs.getString(R.styleable.NaviDrawerMenuView_menu_value)
        } catch (e: Exception) {
            logException(e)
        }
    }

    inline fun setMenuClickedListener(crossinline onClick: (value: String?) -> Unit) {
        this.setOnClickListener {
            onClick(value)
        }
    }
}