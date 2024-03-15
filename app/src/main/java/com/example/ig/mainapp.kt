package com.example.ig


import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.ig.ViewModel.MenuViewModel

class mainapp : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var fragment: Fragment? = null
    private var fragmentManager: FragmentManager? = null
    private lateinit var gestureDetector: GestureDetectorCompat
    private val mainViewModel : MenuViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        toolbar.navigationIcon = null

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val headerView = navigationView.getHeaderView(0)
        val imageView = headerView.findViewById<ImageView>(R.id.ImageView)
        navigationView.setNavigationItemSelectedListener(this)
        imageView.setImageResource(R.drawable.ayano4)
        gestureDetector = GestureDetectorCompat(this, MyGestureListener())
        displayView(3)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val screenWidth = resources.displayMetrics.widthPixels
            if (e1 != null && e2 != null && e2.x - e1.x > screenWidth / 3 && velocityX > 0) {
                val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START)
                }
                return true
            }
            return super.onFling(e1, e2, velocityX, velocityY) ?: false
        }

    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START)
                }
                return true
            }
            R.id.action_settings -> {
                val intent = Intent(this, aboutme::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }
        displayView(3)
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun displayView(position: Int) {
        fragment = null
        val fragmentTags = ""
        when (position) {
            3 -> fragment = menu()
            else -> {
            }
        }
        if (fragment != null) {
            fragmentManager = supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.content_frame, fragment!!, fragmentTags)
                ?.commit()
        }

        mainViewModel.getGithubUser("query_pencarian")
    }


}


