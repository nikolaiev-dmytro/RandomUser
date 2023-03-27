package com.android.randomuser.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.randomuser.databinding.ItemUserListBinding

class UserListAdapter : ListAdapter<UserListItem, UserListViewHolder>(UserListDiffer()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserListViewHolder(ItemUserListBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class UserListDiffer : DiffUtil.ItemCallback<UserListItem>() {
    override fun areItemsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
        return oldItem == newItem
    }

}

class UserListViewHolder(private val binding: ItemUserListBinding) :
    ViewHolder(binding.root) {
    fun bind(user: UserListItem) {
        binding.user = user
        binding.executePendingBindings()
    }
}