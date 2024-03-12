package com.example.ig

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class mainapp : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var fragment: Fragment? = null
    private var fragmentManager: FragmentManager? = null
    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var viewModel: MainAppViewModel

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
        navigationView.setNavigationItemSelectedListener(this)

        gestureDetector = GestureDetectorCompat(this, MyGestureListener())

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(MainAppViewModel::class.java)

        // Check if data is already stored in the ViewModel
        if (viewModel.list.isEmpty()) {
            viewModel.list.addAll(getListIg())
        }

        displayView(0) // call search fragment.
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Pass touch events to GestureDetector
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
            if (e1 != null && e2 != null && e2.x - e1.x > 0 && velocityX > 0) {
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
        // Inflate the menu; this adds items to the action bar if it is present.
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
        displayView(0) // call search fragment.
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun displayView(position: Int) {
        fragment = null
        val fragmentTags = ""
        when (position) {
            0 -> fragment = menu()
            else -> {
            }
        }
        if (fragment != null) {
            fragmentManager = supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.content_frame, fragment!!, fragmentTags)
                ?.commit()
        }

        // Update ViewModel with the new data
        viewModel.list = getListIg()
    }

    private fun getListIg(): ArrayList<ListIg> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listIg = ArrayList<ListIg>()
        for (i in dataName.indices) {
            val dicogram = ListIg(dataName[i], dataPhoto.getResourceId(i, -1))
            listIg.add(dicogram)
        }
        return listIg
    }
}

class MainAppViewModel : ViewModel() {
    var list: ArrayList<ListIg> = ArrayList()
}
