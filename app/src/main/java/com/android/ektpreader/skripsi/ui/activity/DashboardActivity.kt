package com.android.ektpreader.skripsi.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.android.ektpreader.skripsi.R
import com.android.ektpreader.skripsi.databinding.ActivityDashboardBinding
import com.android.ektpreader.skripsi.ui.fragment.AllLogFragment
import com.android.ektpreader.skripsi.ui.fragment.HomeFragment
import com.android.ektpreader.skripsi.ui.fragment.LogFragment
import com.android.ektpreader.skripsi.ui.fragment.SelectFragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private var tag: String? = null
    private var nik: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = " "
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when(newIndex){
                    0->replace(HomeFragment())
                    1->replace(SelectFragment())
                    2->replace(LogFragment())
                    3->replace(AllLogFragment())
                }
                Log.d("bottom_bar", "Selected index: $newIndex, title: ${newTab.title}")
            }

            // An optional method that will be fired whenever an already selected tab has been selected again.
            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
                Log.d("bottom_bar", "Reselected index: $index, title: ${tab.title}")
            }
        })

//        val navView: BottomNavigationView = binding.bottomBar
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.navigation_home, R.id.navigation_profile))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.navigation_home -> {
//                    supportActionBar?.hide()
//                }
//                R.id.navigation_profile -> {
//                    supportActionBar?.hide()
//                }
//                else -> supportActionBar?.show()
//            }
//        }
    }

    private fun replace(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("tag", tag)
        bundle.putString("nik", nik)
        fragment.arguments = bundle
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(R.id.nav_host_fragment_activity_main, fragment)
        transaction.commit()
    }

    companion object {
        fun start(context: Context, tag: String) {
            Intent(context, DashboardActivity::class.java).apply {
                this.putExtra("KEY_TAG", tag)
                context.startActivity(this)
            }
        }
    }
}