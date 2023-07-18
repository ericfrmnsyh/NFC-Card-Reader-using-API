/*
 * *
 *  * Created by rafsanahmad on 7/10/21, 11:55 PM
 *  * Copyright (c) 2021  rafsanahmad. All rights reserved.
 *  * Last modified 7/10/21, 11:55 PM
 *
 */

package com.android.ektpreader.skripsi.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//@Dao
//interface UserDatabaseDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(user: UserEntity)
//
//    @Query("SELECT * FROM user WHERE user_name LIKE :userName")
//    suspend fun getUsername(userName: String): UserEntity
//}