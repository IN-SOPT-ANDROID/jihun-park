package org.sopt.sample.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.HomeRepoContentItemViewBinding
import org.sopt.sample.databinding.HomeRepoTitleItemViewBinding
import org.sopt.sample.presentation.home.data.*
import org.sopt.sample.presentation.home.data.HomeRecycleData.HomeConst.REPO_CONTENT_TYPE
import org.sopt.sample.presentation.home.data.HomeRecycleData.HomeConst.REPO_TITLE_TYPE

class HomeRecyclerAdapter(context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) } //by laze : 초기화를 최대한 늦추는 효과
    private var dataList: List<HomeRecycleData> = emptyList()

    //Repo Content 뷰횰더
    class ContentViewHolder(private val binding: HomeRepoContentItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //2. onCreateViewHolder에서 받아온 binding을 이용하여 HomeItemView의 컴포넌트에 접근할 수 있다.
        fun onContentBind(data: RepoContentDataHome) {
            binding.apply {
                homeItemProfile.setImageDrawable(binding.root.context.getDrawable(data.profileImg))
                homeItemRepoName.text = data.repoName
                homeItemAuthor.text = data.authorName
            }
        }
    }

    //Repo Title 뷰홀더
    class TitleViewHolder(private val binding: HomeRepoTitleItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onTitleBind(data: RepoTitleDataHome) {
            binding.apply {
                homeItemTitle.text = data.titleName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //1. 미리 얻어온 inflater를 이용하여 xml파일인 HomeItemView의 binding을 가져오고 이걸 HomeViewHolder에게 넘겨준다.

        if (viewType == REPO_TITLE_TYPE) {
            val binding = HomeRepoTitleItemViewBinding.inflate(inflater, parent, false)
            return TitleViewHolder(binding)
        }
        //viewType == REPO_CONTENT_TYPE
        else {
            val binding = HomeRepoContentItemViewBinding.inflate(inflater, parent, false)
            return ContentViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //3. 만들어진 onCreateViewHolder에서 만들어진 VieHolder를 이용하여 HomeItemView에 data를 set하는 onBind함수 호출
        if (holder.itemViewType == REPO_TITLE_TYPE) {
            (holder as TitleViewHolder).onTitleBind(dataList[position] as RepoTitleDataHome)
        } else if (holder.itemViewType == REPO_CONTENT_TYPE) {
            (holder as ContentViewHolder).onContentBind(dataList[position] as RepoContentDataHome)
        }
//        holder.onBind(dataList[position])
    }

    //dataList 요소들의 viewType 반환
    override fun getItemViewType(position: Int): Int {
        return dataList[position].viewType
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setDataList(dataList: List<HomeRecycleData>) {
        //깊은복사 얕은복사, 외부의 변화에도 this.dataList가 바뀌지 않도록, toList로 리스트를 새로 만들어준다.
        this.dataList = dataList.toList()
        notifyDataSetChanged() //4. 데이터의 변화가 발생했음을 알림
    }

}