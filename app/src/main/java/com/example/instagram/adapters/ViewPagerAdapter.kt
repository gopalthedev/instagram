package com.example.instagram.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.instagram.fragments.PostFragment
import com.example.instagram.fragments.ProfileFragment
import com.example.instagram.fragments.ReelsFragment

class ViewPagerAdapter(fa: ProfileFragment) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> PostFragment()
            1 -> ReelsFragment()
            else -> {
                PostFragment()
            }
        }
    }
}