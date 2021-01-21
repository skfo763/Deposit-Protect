package com.skfo763.base.extension

import android.content.Context
import kotlin.math.roundToInt

fun Context.dp(dp: Int) = (dp * resources.displayMetrics.density).roundToInt()

fun Context.dp(dp: Float) = (dp * resources.displayMetrics.density)