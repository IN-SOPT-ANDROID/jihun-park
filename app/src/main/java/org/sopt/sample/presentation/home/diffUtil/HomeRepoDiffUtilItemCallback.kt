package org.sopt.sample.presentation.home.diffUtil

import androidx.recyclerview.widget.DiffUtil
import org.sopt.sample.presentation.home.data.HomeRecycleData

class HomeRepoDiffUtilItemCallback:DiffUtil.ItemCallback<HomeRecycleData>() {
    override fun areItemsTheSame(oldItem: HomeRecycleData, newItem: HomeRecycleData): Boolean {
        //oldItem과 newItem의 id 비교
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HomeRecycleData, newItem: HomeRecycleData): Boolean {
        //oldItem과 newItem의 내용 비교
        return oldItem.equals(newItem)
    }

}