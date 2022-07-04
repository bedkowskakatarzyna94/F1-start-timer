package com.example.fadinglightsapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fadinglightsapp.R
import com.example.fadinglightsapp.adapters.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tabTitles = listOf("game", "rank")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = PagerAdapter(
            supportFragmentManager,
            2,
            lifecycle
        )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }
}
