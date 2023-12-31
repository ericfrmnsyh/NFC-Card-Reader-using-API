package com.android.ektpreader.skripsi.pdf

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.FileProvider
import com.android.ektpreader.skripsi.R
import com.android.ektpreader.skripsi.data.response.DataItem
import com.android.ektpreader.skripsi.databinding.LayoutTidakmampuBinding
import java.io.File
import java.io.FileOutputStream

@Suppress("DEPRECATION")
class SKTidakMampu {
    private fun createBitmapFromView(
        context: Context,
        view: View,
        pdfDetails: DataItem,
        activity: Activity
    ): Bitmap {
        val binding = LayoutTidakmampuBinding.bind(view)
        //binding.lifecycleOwner = binding.root.findViewTreeLifecycleOwner()    --   To be used when working with lifecycle components
        binding.apply {
            dataNama.text = pdfDetails.nama
            dataNik.text = pdfDetails.nik
            dataTempat.text = pdfDetails.tempatLahir
            dataTanggal.text = pdfDetails.tanggalLahir.toString()
            dataAlamat.text = pdfDetails.alamat
            dataAgama.text = pdfDetails.agama
            dataStatus.text = pdfDetails.statusPerkawinan
            dataPekerjaan.text = pdfDetails.pekerjaan
            dataKw.text = pdfDetails.kewarganegaraan
        }
        //binding.executePendingBindings()
        return createBitmap(context, binding.root, activity)
    }

    private fun createBitmap(
        context: Context,
        view: View,
        activity: Activity,
    ): Bitmap {
        val displayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context.display?.getRealMetrics(displayMetrics)
            displayMetrics.densityDpi
        } else {
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        }
        view.measure(
            View.MeasureSpec.makeMeasureSpec(
                displayMetrics.widthPixels, View.MeasureSpec.EXACTLY
            ),
            View.MeasureSpec.makeMeasureSpec(
                displayMetrics.heightPixels, View.MeasureSpec.EXACTLY
            )
        )
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth,
            view.measuredHeight, Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return Bitmap.createScaledBitmap(bitmap, 2080, 3820, true)
    }

    private fun convertBitmapToPdf(bitmap: Bitmap, context: Context, nama: String) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        page.canvas.drawBitmap(bitmap, 0F, 0F, null)
        pdfDocument.finishPage(page)
        val filePath = File(context.getExternalFilesDir(null), "SK Tidak Mampu - $nama.pdf")
        pdfDocument.writeTo(FileOutputStream(filePath))
        pdfDocument.close()
        renderPdf(context, filePath)
    }

    @SuppressLint("InflateParams")
    fun createPdf(
        context: Context,
        pdfDetails: DataItem,
        activity: Activity,
        nama: String
    ) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_tidakmampu, null)

        val bitmap = createBitmapFromView(context, view, pdfDetails, activity)
        convertBitmapToPdf(bitmap, activity, nama)
    }

    private fun renderPdf(context: Context, filePath: File) {
        val uri = FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".provider",
            filePath
        )
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setDataAndType(uri, "application/pdf")

        try {
            context.startActivity(intent)
        } catch (_: ActivityNotFoundException) {

        }
    }
}