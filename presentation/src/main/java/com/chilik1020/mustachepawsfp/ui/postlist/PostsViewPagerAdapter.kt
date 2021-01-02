package com.chilik1020.mustachepawsfp.ui.postlist

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PostsViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = mutableListOf<Fragment>()

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]

    fun setFragments(frags: List<Fragment>) {
        fragments.apply {
            clear()
            addAll(frags)
        }
    }
}