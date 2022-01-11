package com.krishana.androidhackathontemplates

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.krishana.androidhackathontemplates.fragments.HomeFragment
import com.krishana.androidhackathontemplates.fragments.SettingsFragment
import com.krishana.androidhackathontemplates.fragments.SignOutFragment

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)

        // All stuffs related to Navigations
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController


        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close).syncState()
        toolbar.title = getCurrentFragment(R.id.nav_home)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)


    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else
        super.onBackPressed()
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment = supportFragmentManager.beginTransaction()
        val destinationFragment: Fragment = when(item.itemId){
            R.id.nav_signout -> SignOutFragment()
            R.id.nav_settings -> SettingsFragment()
            else -> HomeFragment()
        }

        fragment.replace(R.id.fragment_container_view,destinationFragment).commit()
        toolbar.title = getCurrentFragment(item.itemId)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun getCurrentFragment(fragmentid : Int) : String{
        return when(fragmentid){
            R.id.nav_signout -> "Sign Out"
            R.id.nav_settings -> "Settings"
            else -> "Home"
        }
    }

}