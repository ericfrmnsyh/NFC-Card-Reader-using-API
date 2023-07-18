package com.android.ektpreader.skripsi.data.database

import android.content.Context
import androidx.room.*

//@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
//abstract class UserDatabase : RoomDatabase() {
//    abstract fun userDao(): UserDatabaseDao
//    companion object {
//        @Volatile
//        private var INSTANCE: UserDatabase? = null
//        @JvmStatic
//        fun getDatabase(context: Context): UserDatabase {
//            if (INSTANCE == null) {
//                synchronized(UserDatabase::class.java) {
//                    INSTANCE = Room.databaseBuilder(context.applicationContext,
//                        UserDatabase::class.java, "db_workshop")
//                        .createFromAsset("database/user.db")
//                        .build()
//                }
//            }
//            return INSTANCE as UserDatabase
//        }
//    }
//}