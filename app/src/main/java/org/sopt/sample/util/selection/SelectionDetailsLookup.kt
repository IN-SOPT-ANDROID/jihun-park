package org.sopt.sample.util.selection

import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.presentation.gallery.viewholder.GalleryImgViewHolder

//select된 viewholder의 내용을 가져오는 클래스
class SelectionDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y) ?: return null

        val holder = recyclerView.getChildViewHolder(view)
        return if (holder is GalleryImgViewHolder) {
            object : ItemDetails<Long>() {
                override fun getPosition(): Int {
                    return holder.adapterPosition
                }

                override fun getSelectionKey(): Long {
                    return holder.itemId
                }
            }
        } else {
            null
        }
    }
}