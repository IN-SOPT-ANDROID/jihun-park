package org.sopt.sample.util.diffUtil

import androidx.recyclerview.widget.DiffUtil

interface DiffUtilEquals{
    fun <T> equals(oldItem:T, newItem:T):Boolean?
}

class DiffUtilItemCallback<T> :DiffUtil.ItemCallback<T>(), DiffUtilEquals {

    override fun <T> equals(olditem: T, newItem: T): Boolean {
        return olditem!! == newItem
    }

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        //oldItem과 newItem의 id 비교
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem:T, newItem: T): Boolean {
        //oldItem과 newItem의 내용 비교
        return equals(oldItem,newItem)
    }
}