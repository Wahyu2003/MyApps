package com.Try.MyApps.ui.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_users")
data class FavoriteUser(
    @PrimaryKey
    val login: String,
    val avatar_url: String,
    val html_url: String
)

