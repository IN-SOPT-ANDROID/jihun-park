package org.sopt.sample.presentation.gallery.diffUtilAdapter

import androidx.recyclerview.widget.DiffUtil
import org.sopt.sample.presentation.gallery.data.GalleryRecycleData

class GalleryDiffUtilItemCallback:DiffUtil.ItemCallback<GalleryRecycleData>() {

    override fun areItemsTheSame(oldItem: GalleryRecycleData, newItem: GalleryRecycleData): Boolean {
        //oldItem과 newItem의 id 비교
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GalleryRecycleData, newItem: GalleryRecycleData): Boolean {
        //oldItem과 newItem의 내용 비교
        return oldItem.equals(newItem)
    }


}