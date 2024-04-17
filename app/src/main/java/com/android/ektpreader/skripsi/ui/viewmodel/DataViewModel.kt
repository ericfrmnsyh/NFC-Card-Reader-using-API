package com.android.ektpreader.skripsi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {
    val tag = MutableLiveData<String>()
    val nik = MutableLiveData<String>()
}