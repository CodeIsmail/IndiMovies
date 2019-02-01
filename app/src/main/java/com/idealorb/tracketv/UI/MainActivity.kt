package com.idealorb.tracketv.UI

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.idealorb.tracketv.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)

        val navController = findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_nav, navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private val SHARED_PREFS_FILE = "shared_pref"
    }
}
