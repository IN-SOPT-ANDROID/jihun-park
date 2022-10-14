package org.sopt.sample.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import org.sopt.sample.databinding.HomeRepoContentItemViewBinding
import org.sopt.sample.databinding.HomeRepoTitleItemViewBinding
import org.sopt.sample.presentation.home.data.HomeRecycleData
import org.sopt.sample.presentation.home.data.HomeRepoContentData
import org.sopt.sample.presentation.home.data.HomeRepoTitleData

abstract class HomeViewHolder(private val binding:ViewBinding):RecyclerView.ViewHolder(binding.root){
    abstract fun onBind(data: HomeRecycleData)
}
//Repo Content 뷰횰더
class RepoContentViewHolder(private val binding: HomeRepoContentItemViewBinding) :HomeViewHolder(binding) {
    //2. onCreateViewHolder에서 받아온 binding을 이용하여 HomeItemView의 컴포넌트에 접근할 수 있다.
    override fun onBind(data: HomeRecycleData) {
        val contentData = data as HomeRepoContentData
        binding.apply {
            homeItemProfile.setImageDrawable(binding.root.context.getDrawable(contentData.profileImg))
            homeItemRepoName.text = contentData.repoName
            homeItemAuthor.text = contentData.authorName
        }
    }

}
//Repo Title 뷰홀더
class RepoTitleViewHolder(private val binding: HomeRepoTitleItemViewBinding) : HomeViewHolder(binding){
    override fun onBind(data: HomeRecycleData) {
        val titleData = data as HomeRepoTitleData
        binding.apply {
            homeItemTitle.text = titleData.titleName
        }
    }
}