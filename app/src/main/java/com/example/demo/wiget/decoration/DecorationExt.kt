package com.example.demo.wiget.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DecorationExt : RecyclerView.ItemDecoration() {

    private var spanCount = 4
    private var spaceAuto = true
    private var maxWithDPI: Int? = null

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (spaceAuto) maxWithDPI?.let {
            val space = (it - (90 * spanCount)) / (spanCount + 1)
            outRect.set(space, space, space, space)
        }
    }

    fun setSpanCount(spanCount: Int) {
        this.spanCount = spanCount
    }

    fun setMaxWithDPI(maxWithDPI: Int) {
        this.maxWithDPI = maxWithDPI
    }
}