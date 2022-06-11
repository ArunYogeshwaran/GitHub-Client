package com.ayogeshwaran.githubclient.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Given margin will be used for all the four sides.
 */
class MarginItemDecoration(private val margin: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = margin
            left = margin
            right = margin
            bottom = margin
        }
    }
}