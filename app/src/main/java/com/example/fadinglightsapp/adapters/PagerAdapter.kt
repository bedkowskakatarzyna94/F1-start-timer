package com.example.fadinglightsapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fadinglightsapp.fragments.GameFragment
import com.example.fadinglightsapp.fragments.RankingFragment

class PagerAdapter(fm: FragmentManager, private val numberOfFrags: Int, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount() = numberOfFrags

    override fun createFragment(position: Int): Fragment {
        when (position) {
            GAME_FRAGMENT -> return GameFragment()
            1 -> return RankingFragment()
        }
        throw Exception("should not happened")
    }
    companion object {
        const val GAME_FRAGMENT = 0
    }
}
