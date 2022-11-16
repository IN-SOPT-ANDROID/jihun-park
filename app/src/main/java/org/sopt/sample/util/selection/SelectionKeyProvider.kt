package org.sopt.sample.util.selection

import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.presentation.gallery.viewholder.GalleryImgViewHolder

class SelectionKeyProvider(private val recyclerView: RecyclerView) :
    ItemKeyProvider<Long>(SCOPE_MAPPED) {
    override fun getKey(position: Int): Long {
        val holder = recyclerView.findViewHolderForAdapterPosition(position)
        return holder?.itemId ?: throw IllegalStateException("No Holder")
    }

    override fun getPosition(key: Long): Int {
        val holder = recyclerView.findViewHolderForItemId(key)
        return if (holder is GalleryImgViewHolder) {
            holder.adapterPosition
        } else {
            RecyclerView.NO_POSITION
        }
    }
}