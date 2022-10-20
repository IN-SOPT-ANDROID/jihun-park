package org.sopt.sample.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerDecorationHeight(divHeight: Int): RecyclerView.ItemDecoration() {
    private val divHeight = divHeight
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if(parent.getChildAdapterPosition(view) != (parent.adapter?.itemCount?.minus(1) ?: 1)){
            outRect.bottom = divHeight
        }
    }
}