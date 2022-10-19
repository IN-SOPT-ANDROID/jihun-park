package org.sopt.sample.presentation.gallery.diffUtilAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.sample.R
import org.sopt.sample.databinding.GalleryImgItemViewBinding
import org.sopt.sample.presentation.gallery.data.GalleryRecycleData
import org.sopt.sample.presentation.gallery.viewholder.GalleryImgViewHolder
import org.sopt.sample.presentation.gallery.viewholder.GalleryViewHolder
import org.sopt.sample.util.diffUtil.DiffUtilItemCallback

class GalleryListAdapter(context: Context):ListAdapter<GalleryRecycleData,GalleryViewHolder>(
    DiffUtilItemCallback<GalleryRecycleData>()
) {
    private val inflater by lazy { LayoutInflater.from(context) } //by laze : 초기화를 최대한 늦추는 효과
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryImgViewHolder(GalleryImgItemViewBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        when(holder){
            is GalleryImgViewHolder -> holder.onBind(currentList[position])
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
    fun setData(dataList:List<GalleryRecycleData>){
        val newData = mutableListOf<GalleryRecycleData>()
        newData.addAll(currentList)
        newData.addAll(dataList)
        submitList(newData)
    }
}