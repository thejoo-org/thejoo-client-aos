package com.manjee.thejoo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manjee.thejoo.data.model.UserMembership
import com.manjee.thejoo.databinding.ItemMembershipBinding

class UserMembershipAdapter(private val onClick: (UserMembership) -> Unit) :
    ListAdapter<UserMembership, UserMembershipAdapter.MembershipViewHolder>(
        UserMembershipDiffCallback
    ) {

    class MembershipViewHolder(
        private val binding: ItemMembershipBinding,
        val onClick: (UserMembership) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentUserMembership: UserMembership? = null

        init {
            binding.root.setOnClickListener {
                currentUserMembership?.let {
                    onClick(it)
                }
            }
        }

        fun bind(userMembership: UserMembership) {
            currentUserMembership = userMembership
            binding.membership = userMembership
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembershipViewHolder {
        val view = ItemMembershipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MembershipViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MembershipViewHolder, position: Int) {
        val userMembership = getItem(position)
        holder.bind(userMembership)
    }
}

object UserMembershipDiffCallback : DiffUtil.ItemCallback<UserMembership>() {
    override fun areItemsTheSame(oldItem: UserMembership, newItem: UserMembership): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UserMembership, newItem: UserMembership): Boolean {
        return oldItem.id == newItem.id
    }
}