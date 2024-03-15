package com.example.ig

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.ig.ui.MenuViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class desc : AppCompatActivity() {
    private val mainViewModel: MenuViewModel by viewModels()
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private lateinit var loadingIndicator: ProgressBar
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appbar2)
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        loadingIndicator = findViewById(R.id.progressBar)
        loadingIndicator.visibility = View.VISIBLE
        username = intent.getStringExtra("item_data") ?: ""
        getProfileImage(username)
        sectionsPagerAdapter = SectionsPagerAdapter(this,username)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        tabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tab_text_1)
                1 -> getString(R.string.tab_text_2)
                else -> null
            }
        }.attach()

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewPager.setCurrentItem(it.position, true)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        getProfileImage(username)
    }

    private fun getProfileImage(username: String) {
        mainViewModel.getGithubDetail(username)
        mainViewModel.GithubUser.observe(this, { userDetails ->
            val profileImageView = findViewById<ImageView>(R.id.profile_image)

            val usn = findViewById<TextView>(R.id.dicogramText)

            val profileUrl = userDetails?.get(0)?.avatarUrl
            if (userDetails != null) {
                usn.text = userDetails.get(0)?.login
                loadingIndicator.visibility = View.GONE
            }
            val nama = findViewById<TextView>(R.id.nama)
            nama.text = userDetails?.get(0)?.name ?: userDetails?.get(0)?.login

            val Mengikuti = findViewById<TextView>(R.id.diikuti)
            Mengikuti.text = userDetails?.get(0)?.following

            val Pengikut = findViewById<TextView>(R.id.pengikut)
            Pengikut.text = userDetails?.get(0)?.followers
            profileUrl?.let {
                Glide.with(this)
                    .load(it)
                    .into(profileImageView)
            }
        })

    }

}
