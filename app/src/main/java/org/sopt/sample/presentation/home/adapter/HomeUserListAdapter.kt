package org.sopt.sample.presentation.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.sample.data.home.model.UserInfo
import org.sopt.sample.databinding.HomeUserProfileItemBinding

class HomeUserListAdapter(private val context: Context)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var userInfo = emptyList<UserInfo>()
    private lateinit var inflater:LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(!::inflater.isInitialized) inflater = LayoutInflater.from(parent.context)
        return HomeUserViewHolder(context,HomeUserProfileItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeUserViewHolder).bind(userInfo[position])
    }

    override fun getItemCount(): Int = userInfo.size

    fun submitUserList(userInfo: List<UserInfo>) {
        this.userInfo = userInfo.toList()
        notifyItemRangeInserted(itemCount, userInfo.size)
    }

    class HomeUserViewHolder(private val context: Context,private val binding: HomeUserProfileItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserInfo) {
            binding.homeItemNameTv.text = user.firstname+user.lastname
            binding.homeItemEmailTv.text = user.email
            Glide.with(context).load(user.avatar).into(binding.homeItemProfileIv)
        }
    }



}