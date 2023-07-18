package com.android.ektpreader.skripsi.data

data class LogModel (
    val id_log_req: Int?,
    val id_admin: Int,
    val tanggal_request: String,
    val tanggal_selesai: String?,
    val nik_request: String,
    var keperluan: String,
    val no_surat: String?
    )