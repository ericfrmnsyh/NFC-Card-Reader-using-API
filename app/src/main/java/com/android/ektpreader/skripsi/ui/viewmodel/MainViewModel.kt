package com.android.ektpreader.skripsi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.ektpreader.skripsi.data.AddModel
import com.android.ektpreader.skripsi.data.LogModel
import com.android.ektpreader.skripsi.data.api.ApiInterface
import com.android.ektpreader.skripsi.data.api.ApiService
import com.android.ektpreader.skripsi.data.response.DataItem
import com.android.ektpreader.skripsi.data.response.LogResponse
import com.android.ektpreader.skripsi.data.response.NewLogResponse
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val listLog = MutableLiveData<List<LogModel>>()
    val dataDetail = MutableLiveData<DataItem>()
    val request = ApiService.buildService(ApiInterface::class.java)

    fun getDetail(): LiveData<DataItem> {
        return dataDetail
    }

    fun getListLog(): LiveData<List<LogModel>> {
        return listLog
    }

    fun uploadLog(date: String, nik: String, keperluan: String) {
        _isLoading.value = true
        val id = 1
        val dataLog = LogModel(null, id, date, null, nik, keperluan, null)
        val call = request.sendLog(dataLog)

        call.enqueue(object : Callback<LogResponse> {
            override fun onResponse(
                call: Call<LogResponse>,
                response: Response<LogResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error!!) {
                        Log.d("Success", "successfully send log activity")
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    val jsonObject = JSONTokener(response.errorBody()!!.string()).nextValue() as JSONObject
                    val message = jsonObject.getString("message")
                    Log.d("Error", message.toString())
                }
            }
            override fun onFailure(call: Call<LogResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun uploadReq(date: String, tag: String) {
        _isLoading.value = true
        val id = 1
        val dataLog = AddModel(null, id, date, tag)
        val call = request.sendReq(dataLog)

        call.enqueue(object : Callback<LogResponse> {
            override fun onResponse(
                call: Call<LogResponse>,
                response: Response<LogResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error!!) {
                        Log.d("Success", "successfully send log activity")
                    }
                } else {
                    Log.e(TAG1, "onFailure: ${response.message()}")
                    val jsonObject = JSONTokener(response.errorBody()!!.string()).nextValue() as JSONObject
                    val message = jsonObject.getString("message")
                    Log.d("Error", message.toString())
                }
            }
            override fun onFailure(call: Call<LogResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG1, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "SendLog"
        private const val TAG1 = "SendReq"
    }
}