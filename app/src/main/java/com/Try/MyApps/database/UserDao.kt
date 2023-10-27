package com.Try.MyApps.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    // Menyimpan user ke database
    @Insert()
    suspend fun insertUser(user: User)

    // Mendapatkan semua user dari database
    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    // Mendapatkan user berdasarkan username dari database
    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?
}
