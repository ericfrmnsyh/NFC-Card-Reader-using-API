package com.android.ektpreader.skripsi.ui.activity

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.android.ektpreader.skripsi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding : ActivityMainBinding
    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null
    private var intentFilters: Array<IntentFilter>? = null
    private var techLists: Array<Array<String>>? = null

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: ")

        val intent = Intent(this, javaClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        intentFilters = arrayOf(IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED))
        techLists = arrayOf(
            arrayOf(android.nfc.tech.NfcA::class.java.name),
            arrayOf(android.nfc.tech.NfcB::class.java.name),
            arrayOf(android.nfc.tech.NfcF::class.java.name),
        )
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        DashboardActivity.start(this, "045D9042475A80")
    }

    @Suppress("DEPRECATION")
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent: intent.action=${intent.action}")
        if (intent.action == NfcAdapter.ACTION_TECH_DISCOVERED) {
            val tag: Tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG) ?: return
            Log.d(TAG, "onNewIntent: tag=$tag")

            val tagIdByte: ByteArray = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID) ?: return
            val tagId = ArrayList<String>()
            tagIdByte.forEach { tagId.add(String.format("%02X", it)) }
            val tagIdJoined: String = tagId.joinToString("")
            Log.d(TAG, "onNewIntent: tagId=$tagId, tagIdJoined=$tagIdJoined")
            DashboardActivity.start(this, tagIdJoined)
            //DetailActivity.start(this, tagIdJoined)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, intentFilters, techLists)
    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")
        nfcAdapter?.disableForegroundDispatch(this)
        super.onPause()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }
}