package org.sopt.sample.presentation.gallery.viewholder

import android.R
import android.util.Log
import android.view.MotionEvent
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.GalleryImgItemViewBinding
import org.sopt.sample.presentation.gallery.data.GalleryRecycleData

abstract class GalleryViewHolder(private val binding: GalleryImgItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(data: GalleryRecycleData)
}

class GalleryImgViewHolder(private val binding: GalleryImgItemViewBinding) :
    GalleryViewHolder(binding) {
    override fun onBind(data: GalleryRecycleData) {
        binding.galleryImgIv.setImageDrawable(binding.root.context.getDrawable(data.galleryImg))
        binding.galleryDescTv.text = data.desc
    }

    //adapter의 position과 item id return
    fun getItemDetail(): ItemDetailsLookup.ItemDetails<Long?> {
        return object : ItemDetailsLookup.ItemDetails<Long?>() {
            override fun getPosition(): Int {
                return adapterPosition;
            }

            override fun getSelectionKey(): Long? {
                return itemId;
            }

            override fun inSelectionHotspot(e: MotionEvent): Boolean {
                return true
            }
        }
    }

    fun setSelectionTracker(selectionTracker: SelectionTracker<Long>?) {
        binding.galleryItemBackground.isActivated = selectionTracker != null && selectionTracker.isSelected(adapterPosition.toLong())
    }
}