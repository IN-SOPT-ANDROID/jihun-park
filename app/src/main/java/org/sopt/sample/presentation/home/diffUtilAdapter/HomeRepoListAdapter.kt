package org.sopt.sample.presentation.home.diffUtilAdapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.sample.R
import org.sopt.sample.databinding.HomeRepoContentItemViewBinding
import org.sopt.sample.databinding.HomeRepoTitleItemViewBinding
import org.sopt.sample.presentation.home.data.HomeRecycleData
import org.sopt.sample.presentation.home.data.HomeRecycleData.HomeConst.REPO_CONTENT_TYPE
import org.sopt.sample.presentation.home.data.HomeRecycleData.HomeConst.REPO_TITLE_TYPE
import org.sopt.sample.presentation.home.data.HomeRepoContentData
import org.sopt.sample.presentation.home.viewholder.HomeViewHolder
import org.sopt.sample.presentation.home.viewholder.RepoContentViewHolder
import org.sopt.sample.presentation.home.viewholder.RepoTitleViewHolder


class HomeRepoListAdapter(context: Context) :
    ListAdapter<HomeRecycleData, HomeViewHolder>(HomeRepoDiffUtilItemCallback()) {
    private val inflater by lazy { LayoutInflater.from(context) } //by laze : 초기화를 최대한 늦추는 효과

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        Log.d("뷰 타입", viewType.toString())
        return when (viewType) {
            REPO_TITLE_TYPE -> RepoTitleViewHolder(
                HomeRepoTitleItemViewBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            REPO_CONTENT_TYPE -> RepoContentViewHolder(
                HomeRepoContentItemViewBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> {
                throw java.lang.IllegalArgumentException()
            }
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        Log.d("만들어진 뷰홀더 타입", holder.toString())
        when (holder) {
            is RepoTitleViewHolder -> holder.onBind(currentList[position])
            is RepoContentViewHolder -> holder.onBind(currentList[position])
        }
    }

    //dataList 요소들의 viewType 반환
    override fun getItemViewType(position: Int): Int {
        return currentList[position].viewType
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    fun setData(dataList: List<HomeRecycleData>) {
        //DiffUtil은 기존 데이터 리스트와 새로운 데이터 리스트의 차이를 분석해서 해당 부분만 갱신한다.
        //따라서 두개의 리스트가 필요하고, 리스트를 갱신할 때에도 새로운 리스트인 newData를 전달해야한다.
//        this.dataList = dataList.toList()
        val newData = mutableListOf<HomeRecycleData>()
        newData.addAll(currentList)
        newData.add(
            HomeRepoContentData(
                R.drawable.home_profile_img_bino2,
                "NEW IN_SOPT",
                "Jihun Park"
            )
        )
        //ListAdapter 내장함수 submitList()
        submitList(newData)
    }

    fun add() {
        val newData = mutableListOf<HomeRecycleData>()
        newData.addAll(currentList)
        newData.add(
            HomeRepoContentData(
                R.drawable.home_profile_img_bino2,
                "NEW IN_SOPT",
                "Jihun Park",
                REPO_CONTENT_TYPE
            ) as HomeRecycleData
        )
        submitList(newData)
        Log.d("MainActivity Create 생성될까?", "dd")
    }

    fun remove() {
        if (currentList.isNotEmpty()) {
            val newData = mutableListOf<HomeRecycleData>()
            newData.addAll(currentList)
            newData.removeAt(0)
            submitList(newData)
        }

    }
}