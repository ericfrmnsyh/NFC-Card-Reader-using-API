package com.android.ektpreader.skripsi.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.android.ektpreader.skripsi.R
import com.android.ektpreader.skripsi.databinding.ActivityDashboardBinding
import com.android.ektpreader.skripsi.helper.Constant
import com.android.ektpreader.skripsi.ui.fragment.AllLogFragment
import com.android.ektpreader.skripsi.ui.fragment.HomeFragment
import com.android.ektpreader.skripsi.ui.fragment.LogFragment
import com.android.ektpreader.skripsi.ui.fragment.SelectFragment
import com.android.ektpreader.skripsi.ui.viewmodel.DataViewModel
import nl.joery.animatedbottombar.AnimatedBottomBar

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private var tag: String? = null
    private lateinit var nik: String
    private lateinit var viewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
//        setSupportActionBar(binding.toolbar)
//        supportActionBar?.title = " "
        setContentView(binding.root)
        supportActionBar?.hide()

        tag = intent?.getStringExtra(Constant.KEY_TAG)
        nik = intent?.getStringExtra(Constant.KEY_NIK).toString()

        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.tag.value = tag

        binding.bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when(newIndex){
//                    0-> tag?.let { HomeFragment.newInstance(it) }?.let { replace(it) }
                    0-> replace(HomeFragment())
                    1-> replace(SelectFragment())
                    2-> replace(LogFragment())
                    3-> replace(AllLogFragment())
                }
                Log.d("bottom_bar", "Selected index: $newIndex, title: ${newTab.title}")
            }

            // An optional method that will be fired whenever an already selected tab has been selected again.
            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
                Log.d("bottom_bar", "Reselected index: $index, title: ${tab.title}")
            }
        })

        // Hide the system bars
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        // Hide the bottom navigation bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    private fun replace(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.addToBackStack(null)
        transaction.replace(R.id.nav_host_fragment_activity_main, fragment).commit()
    }

    companion object {
        fun start(context: Context, tag: String) {
            Intent(context, DashboardActivity::class.java).apply {
                this.putExtra(Constant.KEY_TAG, tag)
                context.startActivity(this)
            }
        }
    }
}