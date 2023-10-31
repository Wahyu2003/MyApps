package com.Try.MyApps.ui.home

import androidx.recyclerview.widget.DiffUtil

class FavoriteUserDiffCallback : DiffUtil.ItemCallback<FavoriteUser>() {
    override fun areItemsTheSame(oldItem: FavoriteUser, newItem: FavoriteUser): Boolean {
        return oldItem.login == newItem.login
    }

    override fun areContentsTheSame(oldItem: FavoriteUser, newItem: FavoriteUser): Boolean {
        return oldItem == newItem
    }
}
