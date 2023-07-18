package com.android.ektpreader.skripsi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.ektpreader.skripsi.R
import com.android.ektpreader.skripsi.data.LogModel
import com.android.ektpreader.skripsi.databinding.ItemLogBinding
import com.android.ektpreader.skripsi.helper.MyDiffUtil

class MainAdapter(private val context: Context,
                  var listLog: MutableList<LogModel> = mutableListOf())
    : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_log, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listLog[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLogBinding.bind(itemView)
        fun bind(dataLog: LogModel) {
            binding.apply {
                tvItemId.text = dataLog.id_log_req.toString()
                tvItemRequest.text = dataLog.tanggal_request
                if (dataLog.tanggal_selesai != null){
                    tvItemSelesai.text = dataLog.tanggal_selesai
                }
                if (dataLog.tanggal_selesai == null) tvItemSelesai.setText(R.string.kosong)
                if (dataLog.keperluan == "1") tvItemKeperluan.setText(R.string.surat1)
                if (dataLog.keperluan == "2") tvItemKeperluan.setText(R.string.surat2)
                if (dataLog.keperluan == "3") tvItemKeperluan.setText(R.string.surat3)
                if (dataLog.keperluan == "4") tvItemKeperluan.setText(R.string.surat4)
                if (dataLog.keperluan == "5") tvItemKeperluan.setText(R.string.surat5)
                if (dataLog.no_surat != null) tvItemNoSurat.text = dataLog.no_surat
                if (dataLog.no_surat == null) tvItemNoSurat.setText(R.string.kosong)
            }
        }
    }

    override fun getItemCount(): Int = listLog.size

    fun setData(newLogList: MutableList<LogModel>){
        val diffUtil = MyDiffUtil(listLog, newLogList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listLog = newLogList
        diffResult.dispatchUpdatesTo(this)
    }
}