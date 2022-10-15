package org.sopt.sample.presentation.gallery.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.GalleryImgItemViewBinding
import org.sopt.sample.presentation.gallery.data.GalleryRecycleData

abstract class GalleryViewHolder(private val binding:GalleryImgItemViewBinding):RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(data: GalleryRecycleData)
}
class GalleryImgViewHolder(private val binding: GalleryImgItemViewBinding):GalleryViewHolder(binding) {
    override fun onBind(data: GalleryRecycleData) {
        binding.galleryImgIv.setImageDrawable(binding.root.context.getDrawable(data.galleryImg))
        binding.galleryDescTv.text = data.desc
    }
}