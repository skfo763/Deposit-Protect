package com.skfo763.component.underbartext

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import com.skfo763.component.R
import com.skfo763.component.databinding.ViewUnderBarInputBinding

class UnderBarInputTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attributeSet, defStyleAttr), View.OnTouchListener {

    data class Attribute(
        @ColorInt var ubColor: Int = Color.GRAY,
        @ColorInt var ubHColor: Int = Color.LTGRAY,
        var text: String = "",
        @ColorInt var textColor: Int = Color.DKGRAY,
        @ColorInt var textHColor: Int = Color.BLACK
    )

    private fun getAttrs(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int): Attribute {
        val attrs = context.theme.obtainStyledAttributes(attributeSet, R.styleable.UnderBarInputTextView, defStyleAttr, 0)
        return try {
            val ubColor = attrs.getColor(R.styleable.UnderBarInputTextView_ub_under_bar_color, Color.GRAY)
            val ubHColor = attrs.getColor(R.styleable.UnderBarInputTextView_ub_under_bar_highlight_color, Color.LTGRAY)
            val text = attrs.getString(R.styleable.UnderBarInputTextView_ub_text) ?: ""
            val textColor = attrs.getColor(R.styleable.UnderBarInputTextView_ub_text_color, Color.DKGRAY)
            val textHColor = attrs.getColor(R.styleable.UnderBarInputTextView_ub_text_highlight_color, Color.BLACK)

            Attribute(ubColor, ubHColor, text, textColor, textHColor)
        } catch (e: Exception) {
            Attribute()
        } finally {
            attrs.recycle()
        }
    }

    val binding = ViewUnderBarInputBinding.inflate(LayoutInflater.from(context), this)
    val attributes = getAttrs(context, attributeSet, defStyleAttr)

    var onTouchEvent: ((View, MotionEvent) -> Boolean)? = null
    var onClickEvent: ((View) -> Unit)? = null

    var text: String = attributes.text
        set(value) {
            binding.underBarInputText.text = value
            binding.underBarInputText.setTextColor(attributes.textHColor)
            field = value
        }

    init {
        setLayout()
        setOnClickListener{
            onClickEvent?.invoke(it)
        }
        setOnTouchListener(this)


        setUnderBar()
        setTextView()
    }

    private fun setLayout() {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    private fun setUnderBar() = with(binding.underBar) {
        this.setBackgroundColor(attributes.ubColor)
    }

    private fun setTextView() = with(binding.underBarInputText) {
        this.text = attributes.text
        this.setTextColor(attributes.textColor)
    }

    override fun onTouch(view: View, motion: MotionEvent): Boolean {
        when(motion.action) {
            MotionEvent.ACTION_DOWN -> {
                binding.underBar.setBackgroundColor(attributes.ubHColor)
                binding.underBarInputText.setTextColor(attributes.textHColor)
            }
            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                binding.underBar.setBackgroundColor(attributes.ubColor)
                binding.underBarInputText.setTextColor(attributes.textColor)
            }
        }
        return onTouchEvent?.invoke(view, motion) ?: false
    }
}
