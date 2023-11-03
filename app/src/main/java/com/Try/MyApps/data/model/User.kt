package com.Try.MyApps.database

import com.google.firebase.database.IgnoreExtraProperties

// anotasi untuk mengabaikan properti tambahan
@IgnoreExtraProperties
data class User(
    var username: String? = "",
    var password: String? = "",
    var email: String? = "",
    var github: String? = ""
)

