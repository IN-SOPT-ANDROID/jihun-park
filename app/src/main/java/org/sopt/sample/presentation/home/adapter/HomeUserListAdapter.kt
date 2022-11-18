package org.sopt.sample.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.sample.data.home.model.Data
import org.sopt.sample.databinding.HomeUserProfileItemBinding
import org.sopt.sample.presentation.home.UserInfo

class HomeUserListAdapter(private val context: Context)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var userInfo = emptyList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HomeUserViewHolder(context,HomeUserProfileItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeUserViewHolder -> holder.bind(userInfo[position])
        }
    }

    override fun getItemCount(): Int = userInfo.size

    fun submitUserList(userInfo: List<Data>) {
        this.userInfo = userInfo.toList()
        notifyItemRangeInserted(itemCount, userInfo.size)
    }

    class HomeUserViewHolder(private val context: Context,private val binding: HomeUserProfileItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Data) {
            binding.homeItemNameTv.text = user.first_name+user.last_name
            binding.homeItemEmailTv.text = user.email
            Glide.with(context).load(user.avatar).into(binding.homeItemProfileIv)
        }
    }



}