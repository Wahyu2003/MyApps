package com.Try.MyApps.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    // Mendapatkan instance dari UserDao
    abstract fun userDao(): UserDao

    companion object {
        // Membuat variabel untuk menyimpan instance dari UserDatabase
        private var INSTANCE: UserDatabase? = null

        // Membuat fungsi untuk mendapatkan instance dari UserDatabase
        fun getInstance(context: Context): UserDatabase? {
            if (INSTANCE == null) {
                synchronized(UserDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "user_database"
                    ).build()
                }
            }
            return INSTANCE
        }

        // Membuat fungsi untuk menghapus instance dari UserDatabase
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

