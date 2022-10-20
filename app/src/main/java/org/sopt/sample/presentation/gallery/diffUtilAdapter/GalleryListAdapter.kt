package org.sopt.sample.presentation.gallery.diffUtilAdapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.ListAdapter
import org.sopt.sample.databinding.GalleryImgItemViewBinding
import org.sopt.sample.presentation.gallery.data.GalleryRecycleData
import org.sopt.sample.presentation.gallery.viewholder.GalleryImgViewHolder
import org.sopt.sample.presentation.gallery.viewholder.GalleryViewHolder
import org.sopt.sample.util.diffUtil.DiffUtilItemCallback

class GalleryListAdapter(context: Context) : ListAdapter<GalleryRecycleData, GalleryViewHolder>(
    DiffUtilItemCallback<GalleryRecycleData>()
) {
    init {
        //id를 이용해 item을 식별하겠다는 설정
        setHasStableIds(true)
    }

    private val inflater by lazy { LayoutInflater.from(context) } //by laze : 초기화를 최대한 늦추는 효과
    private var selectionTracker: SelectionTracker<Long>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryImgViewHolder(GalleryImgItemViewBinding.inflate(inflater, parent, false))
    }

    override fun getItemId(position: Int): Long {
        //각 item의 id를 설정
        return getItem(position).id
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        when (holder) {
            is GalleryImgViewHolder -> {
                holder.onBind(currentList[position])
                holder.getItemDetail()
                holder.setSelectionTracker(this.selectionTracker)
            }
        }
    }

    fun setSelectionTracker(selectionTracker: SelectionTracker<Long>?) {
        this.selectionTracker = selectionTracker
    }
}