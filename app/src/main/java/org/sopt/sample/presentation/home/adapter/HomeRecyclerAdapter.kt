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
import org.sopt.sample.presentation.home.viewholder.HomeViewHolder
import org.sopt.sample.presentation.home.viewholder.RepoContentViewHolder
import org.sopt.sample.presentation.home.viewholder.RepoTitleViewHolder

class HomeRecyclerAdapter(context: Context) :
    RecyclerView.Adapter<HomeViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) } //by laze : 초기화를 최대한 늦추는 효과
    private var dataList: List<HomeRecycleData> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        //1. 미리 얻어온 inflater를 이용하여 xml파일인 HomeItemView의 binding을 가져오고 이걸 HomeViewHolder에게 넘겨준다.
        return when (viewType) {
            //뷰 타입에 따른 분기처리
            REPO_TITLE_TYPE -> RepoTitleViewHolder(HomeRepoTitleItemViewBinding.inflate(inflater, parent, false))
            REPO_CONTENT_TYPE -> RepoContentViewHolder(HomeRepoContentItemViewBinding.inflate(inflater, parent, false))
            else -> throw java.lang.IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        //3. 만들어진 onCreateViewHolder에서 만들어진 VieHolder를 이용하여 HomeItemView에 data를 set하는 onBind함수 호출
        when (holder) {
            //홀더 타입에 따른 분기처리
            is RepoTitleViewHolder -> holder.onBind(dataList[position])
            is RepoContentViewHolder -> holder.onBind(dataList[position])
        }
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