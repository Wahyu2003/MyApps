package com.Try.MyApps.ui.home

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteUser(user: FavoriteUser)

    @Query("DELETE FROM favorite_users WHERE login = :login")
    suspend fun deleteFavoriteUserByLogin(login: String)

    @Query("SELECT * FROM favorite_users")
    fun getFavoriteUsers(): LiveData<List<FavoriteUser>>
}

