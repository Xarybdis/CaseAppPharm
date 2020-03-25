package com.example.caseapppharmy

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.caseapppharmy.ui.authentication.Login
import com.example.caseapppharmy.util.SharedPrefUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar as Toolbar?)

        setupNavBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.bar_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> logoutUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logoutUser() {
        SharedPrefUtils.clearUserCredentials(this)
        startActivity(Intent(this, Login::class.java))
        finish()
    }

    private fun setupNavBar() {
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        val navBarController = findNavController(R.id.nav_host_fragment_container)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_news,
                R.id.navigation_pharmacy,
                R.id.navigation_profile
            )
        )
        this.actionBar?.setDisplayHomeAsUpEnabled(true)

        setupActionBarWithNavController(navBarController, appBarConfiguration)
        navView?.setupWithNavController(navBarController)
    }
}
