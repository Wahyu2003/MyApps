package com.Try.MyApps.ui.home
import androidx.lifecycle.LiveData
import com.Try.MyApps.ui.home.FavoriteUser
import com.Try.MyApps.ui.home.FavoriteUserDao

class FavoriteRepository(private val favoriteUserDao: FavoriteUserDao) {
    val allFavoriteUsers: LiveData<List<FavoriteUser>> = favoriteUserDao.getFavoriteUsers()

    suspend fun insertFavoriteUser(favoriteUser: FavoriteUser) {
        favoriteUserDao.insertFavoriteUser(favoriteUser)
    }

    suspend fun deleteFavoriteUser(login: String) {
        favoriteUserDao.deleteFavoriteUserByLogin(login)
    }
}
