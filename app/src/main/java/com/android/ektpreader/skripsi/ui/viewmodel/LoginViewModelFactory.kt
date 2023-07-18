package com.android.ektpreader.skripsi.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

//class LoginViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory(){
//
//    companion object {
//        @Volatile
//        private var INSTANCE: LoginViewModelFactory? = null
//        @JvmStatic
//        fun getInstance(application: Application): LoginViewModelFactory {
//            if (INSTANCE == null) {
//                synchronized(LoginViewModelFactory::class.java) {
//                    INSTANCE = LoginViewModelFactory(application)
//                }
//            }
//            return INSTANCE as LoginViewModelFactory
//        }
//    }
//
//    @Suppress("Unchecked_cast")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
//            return LoginViewModel(mApplication) as T
//        }
//        throw IllegalArgumentException("Unknown View Model Class")
//    }
//}