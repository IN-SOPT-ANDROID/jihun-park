package org.sopt.sample.presentation.home.diffUtilAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.sample.R
import org.sopt.sample.databinding.HomeRepoContentItemViewBinding
import org.sopt.sample.databinding.HomeRepoTitleItemViewBinding
import org.sopt.sample.presentation.MainActivity
import org.sopt.sample.presentation.home.HomeFragment
import org.sopt.sample.presentation.home.data.*
import org.sopt.sample.presentation.home.viewholder.HomeViewHolder
import org.sopt.sample.presentation.home.viewholder.RepoContentViewHolder
import org.sopt.sample.presentation.home.viewholder.RepoTitleViewHolder
import org.sopt.sample.util.diffUtil.DiffUtilItemCallback


class HomeRepoListAdapter(context: Context) :
    ListAdapter<HomeRecycleData, HomeViewHolder>(DiffUtilItemCallback()) {
    private val inflater by lazy { LayoutInflater.from(context) } //by lazy : 초기화를 최대한 늦추는 효과

    //아이템뷰 타입에 따라 다른 binding inflate 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
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
                throw IllegalArgumentException("${this::class.java.simpleName} error")
            }
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        when (holder) {
            is RepoTitleViewHolder -> holder.onBind(currentList[position])
            is RepoContentViewHolder -> holder.onBind(currentList[position])
        }
    }
    //dataList 요소들의 viewType 반환
    override fun getItemViewType(position: Int): Int {
        val result = when(currentList[position]){
            is HomeRepoTitleData -> REPO_TITLE_TYPE
            is HomeRepoContentData -> REPO_CONTENT_TYPE
            else -> throw IllegalArgumentException("${this::class.java.simpleName} error")
        }
        return result
    }

    fun add() {
        //DiffUtil은 기존 데이터 리스트와 새로운 데이터 리스트의 차이를 분석해서 해당 부분만 갱신한다.
        //따라서 두개의 리스트가 필요하고, 리스트를 갱신할 때에도 새로운 리스트인 newData를 전달해야한다.
        val newData = mutableListOf<HomeRecycleData>()
        newData.addAll(currentList)
        newData.add(
            0,
            HomeRepoContentData(
                R.drawable.home_profile_img_bino2,
                "NEW IN_SOPT",
                "Jihun Park",
            ) as HomeRecycleData
        )
        //ListAdapter 내장함수 submitList()
        submitList(newData)

        //Repo 추가 후 scrollToTop
        ((MainActivity.mContext as MainActivity).supportFragmentManager.findFragmentByTag(
            HomeFragment::class.java.simpleName
        ) as HomeFragment).scrollToTop()
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