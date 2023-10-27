package com.Try.MyApps.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _profileImageUrl = MutableLiveData<String>()
    val profileImageUrl: LiveData<String> get() = _profileImageUrl
    // Fungsi untuk mendapatkan data pengguna yang sedang login
    fun getLoggedInUser(userId: Int): LiveData<User> {
        return userRepository.getUserById(userId)
    }

    fun setProfileImageUrl(url: String) {
        _profileImageUrl.value = url
    }

}
