package org.sopt.sample.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.HomeItemViewBinding
import org.sopt.sample.presentation.home.data.HomeRecycleData

class HomeRecyclerAdapter(context:Context):RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {
    private val inflater by lazy{LayoutInflater.from(context)} //by laze : 초기화를 최대한 늦추는 효과
    private var dataList:List<HomeRecycleData> = emptyList()
    class HomeViewHolder(private val binding:HomeItemViewBinding):RecyclerView.ViewHolder(binding.root){
        //2. onCreateViewHolder에서 받아온 binding을 이용하여 HomeItemView의 컴포넌트에 접근할 수 있다.
        fun onBind(data: HomeRecycleData){
            binding.apply {
                homeItemProfile.setImageDrawable(binding.root.context.getDrawable(data.profileImg))
                homeItemRepoName.text = data.repoName
                homeItemAuthor.text = data.authorName
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        //1. 미리 얻어온 inflater를 이용하여 xml파일인 HomeItemView의 binding을 가져오고 이걸 HomeViewHolder에게 넘겨준다.
        val binding = HomeItemViewBinding.inflate(inflater,parent,false)
        return HomeViewHolder(binding)

    }
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        //3. 만들어진 onCreateViewHolder에서 만들어진 VieHolder를 이용하여 HomeItemView에 data를 set하는 onBind함수 호출
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    fun setDataList(dataList:List<HomeRecycleData>){
        //깊은복사 얕은복사, 외부의 변화에도 this.dataList가 바뀌지 않도록, toList로 리스트를 새로 만들어준다.
        this.dataList = dataList.toList()
        notifyDataSetChanged() //4. 데이터의 변화가 발생했음을 알림
    }
}