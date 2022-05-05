package com.example.mypager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyFragmentPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    val fragments : List<Fragment>
    init {
        fragments = listOf(OneFragment(), TwoFragment())
    }

    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}