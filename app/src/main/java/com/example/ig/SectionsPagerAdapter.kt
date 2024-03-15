package com.example.ig

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import follower
import following

class SectionsPagerAdapter(activity: AppCompatActivity, private val username : String) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = follower.newInstance(username)
            1 -> fragment = following.newInstance(username)
        }
        return fragment as Fragment
    }

}