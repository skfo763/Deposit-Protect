package com.skfo763.depositprotect.main.component

import android.content.Context
import android.graphics.PointF
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class FastScrollLayoutManager(context: Context): LinearLayoutManager(context) {

    var scrollSpeedFactor: Int = 1

    private fun scroller(context: Context, speedFactor: Int): LinearSmoothScroller = object: LinearSmoothScroller(context) {
        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
            return this@FastScrollLayoutManager.computeScrollVectorForPosition(targetPosition)
        }

        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
            return super.calculateSpeedPerPixel(displayMetrics) / speedFactor
        }
    }

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) = with(scroller(recyclerView.context, scrollSpeedFactor)) {
        targetPosition = position
        startSmoothScroll(this)
    }
}

fun RecyclerView.fastScrollTo(position: Int, factor: Int) {
    if(isLayoutSuppressed) return
    (layoutManager as? FastScrollLayoutManager)?.let {
        it.scrollSpeedFactor = factor
        this.smoothScrollToPosition(position)
    } ?: run {

    }
}