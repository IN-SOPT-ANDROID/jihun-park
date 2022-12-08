package org.sopt.sample.presentation.music.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import org.sopt.sample.databinding.MusicInfoItemBinding
import org.sopt.sample.presentation.music.data.MusicData
import org.sopt.sample.presentation.music.data.MusicInfo
import org.sopt.sample.util.DiffUtilItemCallback

class MusicListAdapter(private val context: Context) :
    ListAdapter<MusicData, MusicViewHolder>(DiffUtilItemCallback()) {
    private val inflater by lazy { LayoutInflater.from(context) }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicInfoViewHolder(MusicInfoItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    fun setData(dataList: List<MusicData>) {
        submitList(dataList)
    }

    fun addData(data: MusicData) {
        val newData = mutableListOf<MusicData>()
        newData.addAll(currentList)
        newData.add(data)
        submitList(newData)
    }
    fun removeData(position: Int){
        if(currentList.isNotEmpty()){
            val newData = mutableListOf<MusicData>()
            newData.addAll(currentList)
            newData.removeAt(position)
            submitList(newData)
        }
    }
}

abstract class MusicViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(data: MusicData)
}

class MusicInfoViewHolder(private val binding: MusicInfoItemBinding) : MusicViewHolder(binding) {
    override fun onBind(data: MusicData) {
        val infoData = data as MusicInfo
        binding.musicItemIv.load(infoData.img)
        binding.musicItemTitleTv.text = infoData.title
        binding.musicItemSingerTv.text = infoData.singer
    }
}
