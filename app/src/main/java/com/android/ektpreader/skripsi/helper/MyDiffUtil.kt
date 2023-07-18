package com.android.ektpreader.skripsi.helper

import androidx.recyclerview.widget.DiffUtil
import com.android.ektpreader.skripsi.data.LogModel

class MyDiffUtil(
    private val oldList: MutableList<LogModel>,
    private val newList: MutableList<LogModel>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id_log_req == newList[newItemPosition].id_log_req
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].id_log_req != newList[newItemPosition].id_log_req -> {
                false
            }
            oldList[oldItemPosition].tanggal_request != newList[newItemPosition].tanggal_request -> {
                false
            }
            oldList[oldItemPosition].tanggal_selesai != newList[newItemPosition].tanggal_selesai -> {
                false
            }
            oldList[oldItemPosition].keperluan != newList[newItemPosition].keperluan -> {
                false
            }
            oldList[oldItemPosition].no_surat != newList[newItemPosition].no_surat -> {
                false
            }
            else -> true
        }
    }
}