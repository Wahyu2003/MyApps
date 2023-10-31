package com.Try.MyApps.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.Try.MyApps.R
import com.squareup.picasso.Picasso

class FavoriteUserAdapter(private val onItemClick: (FavoriteUser) -> Unit) :
    ListAdapter<FavoriteUser, FavoriteUserAdapter.FavoriteUserViewHolder>(FavoriteUserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_user, parent, false)
        return FavoriteUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener { onItemClick(user) }
    }

    class FavoriteUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView: TextView = itemView.findViewById(R.id.tvUserName)
        private val userAvatarImageView: ImageView = itemView.findViewById(R.id.ivUserAvatar)
        fun bind(user: FavoriteUser) {
            userNameTextView.text = user.login
            // Load gambar avatar pengguna
            Picasso.get().load(user.avatar_url).into(userAvatarImageView)
        }
    }
}
