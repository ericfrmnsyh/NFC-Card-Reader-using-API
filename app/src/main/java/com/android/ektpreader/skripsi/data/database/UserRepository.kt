/*
 * *
 *  * Created by rafsanahmad on 7/10/21, 11:55 PM
 *  * Copyright (c) 2021  rafsanahmad. All rights reserved.
 *  * Last modified 7/10/21, 11:55 PM
 *
 */

package com.android.ektpreader.skripsi.data.database

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

//class UserRepository(application: Application) {
//    private val userDao: UserDatabaseDao
//    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
//
//    init {
//        val db = UserDatabase.getDatabase(application)
//        userDao = db.userDao()
//    }
//
//    suspend fun insert(user: UserEntity) {
//        userDao.insert(user)
//    }
//
//    suspend fun getUsername(email: String): UserEntity {
//        return userDao.getUsername(email)
//    }
//
//}