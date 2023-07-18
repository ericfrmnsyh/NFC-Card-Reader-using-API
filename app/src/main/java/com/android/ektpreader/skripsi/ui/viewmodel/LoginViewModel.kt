package com.android.ektpreader.skripsi.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch


//class LoginViewModel(application: Application) : ViewModel(){
//    private val repository: UserRepository = UserRepository(application)
//    private var _account = MutableLiveData<UserEntity?>()
//    val account get() = _account
//
//
//    fun login(email:String) {
//       viewModelScope.launch {
//           _account.value = repository.getUsername(email)
//       }
//    }
//}