package com.example.ig

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.ig.Helper.SettingPreferences
import com.example.ig.Helper.ViewModelFactory
import com.example.ig.Helper.dataStore
import com.example.ig.ViewModel.MenuViewModel
import com.example.ig.ViewModel.ThemeViewModel
import com.google.android.material.navigation.NavigationView

class mainapp : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var fragment: Fragment? = null
    private var fragmentManager: FragmentManager? = null
    private lateinit var themeViewModel: ThemeViewModel
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private val mainViewModel: MenuViewModel by viewModels()
    private lateinit var gestureDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerToggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerToggle.drawerArrowDrawable.color = android.graphics.Color.BLACK
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.isDrawerSlideAnimationEnabled =true
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val headerView = navigationView.getHeaderView(0)
        val imageView = headerView.findViewById<ImageView>(R.id.ImageView)
        imageView.setImageResource(R.drawable.ayano4)

        val applicationInstance: Application = application
        val pref = SettingPreferences.getInstance(applicationInstance.dataStore)
        val factory = ViewModelFactory.getInstance(applicationInstance, pref)
        themeViewModel = ViewModelProvider(this, factory).get(ThemeViewModel::class.java)

        gestureDetector = GestureDetectorCompat(this, MyGestureListener())

        themeViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }


        displayView(3)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // Do something when settings is clicked
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> {
                val intent = Intent(this, FavList::class.java)
                startActivity(intent)
            }
            R.id.theme -> {
                val currentNightMode = AppCompatDelegate.getDefaultNightMode()
                val isDarkModeActive = currentNightMode == AppCompatDelegate.MODE_NIGHT_YES
                themeViewModel.saveThemeSetting(isDarkModeActive)
                recreate()
            }
            R.id.aboutme -> {
                val intent = Intent(this, aboutme::class.java)
                startActivity(intent)
            }
        }
        displayView(3)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun displayView(position: Int) {
        fragment = null
        val fragmentTags = ""
        when (position) {
            3 -> fragment = menu()
        }
        if (fragment != null) {
            fragmentManager = supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.content_frame, fragment!!, fragmentTags)
                ?.commit()
        }
        mainViewModel.getGithubUser("query_pencarian")
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return if (ev != null && gestureDetector.onTouchEvent(ev)) {
            true
        } else {
            super.dispatchTouchEvent(ev)
        }
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val screenWidth = resources.displayMetrics.widthPixels
            if (e1 != null && e2.x - e1.x > screenWidth / 3 && velocityX > 0) {
                val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START)
                }
                return true
            }
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }
}
