package org.sopt.sample.presentation.music.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import org.sopt.sample.databinding.MusicAddItemBinding
import org.sopt.sample.databinding.MusicInfoItemBinding
import org.sopt.sample.presentation.music.data.*
import org.sopt.sample.presentation.music.dialog.MusicAddDialog
import org.sopt.sample.util.DiffUtilItemCallback

class MusicListAdapter(
    private val context: Context,
    private val childFragmentManager: FragmentManager
) :
    ListAdapter<MusicData, MusicViewHolder>(DiffUtilItemCallback()) {
    private val inflater by lazy { LayoutInflater.from(context) }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return when (viewType) {
            MUSIC_INFO_TYPE -> MusicInfoViewHolder(
                MusicInfoItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            MUSIC_ADD_TYPE -> MusicAddViewHolder(
                childFragmentManager,
                MusicAddItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> {
                throw  IllegalArgumentException("${this::class.java.simpleName} error")
            }
        }

    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {

        holder.onBind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size
    override fun getItemViewType(position: Int): Int {
        val result = when (currentList[position]) {
            is MusicInfo -> MUSIC_INFO_TYPE
            is MusicAdd -> MUSIC_ADD_TYPE
            else -> throw IllegalArgumentException("${this::class.java.simpleName} error")
        }
        return result
    }

    fun setData(dataList: List<MusicData>) {
        submitList(dataList)
    }

    fun addData(data: MusicData) {
        val newData = mutableListOf<MusicData>()
        newData.addAll(currentList)
        newData.add(data)
        submitList(newData)
    }

    fun removeData(position: Int) {
        if (currentList.isNotEmpty()) {
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

class MusicAddViewHolder(
    private val childFragmentManager: FragmentManager, //음악 추가 버튼 클릭 시, 다이얼로그 show() 호출을 위해 전달하는 인자
    private val binding: MusicAddItemBinding
) : MusicViewHolder(binding) {
    override fun onBind(data: MusicData) {
        val addData = data as MusicAdd
        val dialog = MusicAddDialog()
        binding.musicItemAddBtn.apply {
            setImageResource(addData.addRes)
            setOnClickListener {
                dialog.isCancelable = false //배경클릭 금지
                dialog.show(childFragmentManager, "MusicAddDialog")
            }
        }
    }
}