package com.android.ektpreader.skripsi.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.ektpreader.skripsi.R
import com.android.ektpreader.skripsi.databinding.ActivitySelectBinding
import com.android.ektpreader.skripsi.helper.Constant
import com.android.ektpreader.skripsi.ui.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class SelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectBinding
    private lateinit var mainViewModel: MainViewModel
    private var nik: String? = null
    private var tag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = null

        connectViewModel()

        nik = intent?.getStringExtra(Constant.KEY_NIK)
        tag = intent?.getStringExtra(Constant.KEY_TAG)
        nik?.let { tag?.let { it1 -> select(it, it1) } }
    }

    private fun connectViewModel(){
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_ktp, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_repeat -> {
                startActivity(Intent(this, MainActivity::class.java) )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private fun select(nik: String, tag: String) {
        val date = getCurrentDateTime()
        val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")

        binding.card1.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "1")
            Toast.makeText(this@SelectActivity, "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            DetailActivity.start(this, tag)
        }
        binding.card2.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "2")
            Toast.makeText(this@SelectActivity, "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            DetailActivity.start(this, tag)
        }
        binding.card3.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "3")
            Toast.makeText(this@SelectActivity, "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            DetailActivity.start(this, tag)
        }
        binding.card4.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "4")
            Toast.makeText(this@SelectActivity, "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            DetailActivity.start(this, tag)
        }
        binding.card5.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "5")
            Toast.makeText(this@SelectActivity, "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            DetailActivity.start(this, tag)
        }
    }

    companion object {
        fun start(context: Context, nik: String, tag: String) {
            Intent(context, SelectActivity::class.java).apply {
                this.putExtra("KEY_NIK", nik)
                this.putExtra("KEY_TAG", tag)
                context.startActivity(this)
            }
        }
    }
}