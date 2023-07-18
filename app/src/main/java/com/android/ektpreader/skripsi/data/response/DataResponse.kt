package com.android.ektpreader.skripsi.data.response

import com.android.ektpreader.skripsi.data.LogModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataResponse(

	@field:SerializedName("totaldata")
	val totaldata: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("messsage")
	val messsage: String? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataItem(

	@field:SerializedName("desa")
	val desa: String? = null,

	@field:SerializedName("rt")
	@Expose
	val rt: String? = null,

	@field:SerializedName("rw")
	@Expose
	val rw: String? = null,

	@field:SerializedName("agama")
	@Expose
	val agama: String? = null,

	@field:SerializedName("statusPerkawinan")
	@Expose
	val statusPerkawinan: String? = null,

	@field:SerializedName("tanggalLahir")
	@Expose
	val tanggalLahir: String? = null,

	@field:SerializedName("alamat")
	@Expose
	val alamat: String? = null,

	@field:SerializedName("kewarganegaraan")
	@Expose
	val kewarganegaraan: String? = null,

	@field:SerializedName("nik")
	@Expose
	val nik: String? = null,

	@field:SerializedName("nama")
	@Expose
	val nama: String? = null,

	@field:SerializedName("pekerjaan")
	@Expose
	val pekerjaan: String? = null,

	@field:SerializedName("kecamatan")
	@Expose
	val kecamatan: String? = null,

	@field:SerializedName("tempatLahir")
	@Expose
	val tempatLahir: String? = null,

	@field:SerializedName("jenisKelamin")
	@Expose
	val jenisKelamin: String? = null,

	@field:SerializedName("tag")
	@Expose
	val tag: String? = null,

	@field:SerializedName("golonganDarah")
	@Expose
	val golonganDarah: String? = null,
)

data class LogResponse(

	@field:SerializedName("messsage")
	val messsage: Messsage? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Messsage(

	@field:SerializedName("success")
	val success: String? = null
)

data class NewLogResponse(

	@field:SerializedName("totaldata")
	val totaldata: Int? = null,

	@field:SerializedName("messsage")
	val messsage: String? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("data")
	val list_log: List<LogModel>,
)