package com.Try.MyApps.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Try.MyApps.R
import com.Try.MyApps.model.GitHubUser
import com.squareup.picasso.Picasso

class UserAdapter(private val userList: List<GitHubUser>, private val clickListener: (GitHubUser) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userAvatar: ImageView = itemView.findViewById(R.id.itemAvatar)
        val userName: TextView = itemView.findViewById(R.id.itemName)
        val userUrl: TextView = itemView.findViewById(R.id.itemGithub)
        val heartIcon: ImageView = itemView.findViewById(R.id.itemHeartIcon) // Tambahkan ikon hati

        fun bind(user: GitHubUser, clickListener: (GitHubUser) -> Unit) {
            userName.text = user.login
            userUrl.text = user.html_url

            // Menggunakan Picasso untuk memuat gambar profil
            Picasso.get().load(user.avatar_url).into(userAvatar)

            // Set ikon hati sesuai dengan status "favorit"
            if (user.isFavorite) {
                heartIcon.setImageResource(R.drawable.ic_heart_red)
            } else {
                heartIcon.setImageResource(R.drawable.ic_heart_gray)
            }

            // Handle klik pada item atau ikon hati
            itemView.setOnClickListener {
                clickListener(user)
            }

            heartIcon.setOnClickListener {
                clickListener(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.bind(currentUser, clickListener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
