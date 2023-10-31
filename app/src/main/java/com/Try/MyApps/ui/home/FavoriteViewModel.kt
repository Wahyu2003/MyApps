package com.Try.MyApps.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val favoriteUserDao: FavoriteUserDao
    val allFavoriteUsers: LiveData<List<FavoriteUser>>

    init {
        val database = FavoriteUserDatabase.getDatabase(application)
        favoriteUserDao = database.favoriteUserDao()
        allFavoriteUsers = favoriteUserDao.getFavoriteUsers()
    }

    fun insertFavoriteUser(favoriteUser: FavoriteUser) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteUserDao.insertFavoriteUser(favoriteUser)
        }
    }

    fun deleteFavoriteUser(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteUserDao.deleteFavoriteUserByLogin(login)
        }
    }

}


