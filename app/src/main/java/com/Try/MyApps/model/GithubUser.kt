package com.Try.MyApps.model

data class GitHubUser(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val html_url: String,
    var isFavorite: Boolean = false
    )
