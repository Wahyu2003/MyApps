package com.Try.MyApps.database

import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

// anotasi untuk mengabaikan properti tambahan
@IgnoreExtraProperties
data class User(
    var username: String? = "",
    var password: String? = "",
    var email: String? = "",
    var github: String? = "",
//    val photoUrl: String = "https://example.com/default-profile.png"
)

