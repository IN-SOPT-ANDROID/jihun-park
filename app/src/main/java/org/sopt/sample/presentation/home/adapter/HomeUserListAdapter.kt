package org.sopt.sample.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.sample.data.home.model.User
import org.sopt.sample.databinding.HomeUserProfileItemBinding

class HomeUserListAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var userInfo = emptyList<User>()
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeUserViewHolder(
            context,
            HomeUserProfileItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeUserViewHolder).bind(userInfo[position])
    }

    override fun getItemCount(): Int = userInfo.size

    fun submitUserList(userInfo: List<User>) {
        this.userInfo = userInfo.toList()
        notifyItemRangeInserted(itemCount, userInfo.size)
    }

    class HomeUserViewHolder(
        private val context: Context,
        private val binding: HomeUserProfileItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.homeItemNameTv.text = user.name
            binding.homeItemEmailTv.text = user.email
            Glide.with(context).load(user.avatar).into(binding.homeItemProfileIv)
        }
    }


}