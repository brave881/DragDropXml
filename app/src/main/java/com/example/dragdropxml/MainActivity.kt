package com.example.dragdropxml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.dragdropxml.databinding.ActivityMainBinding
import com.example.dragdropxml.ui.BoardFragment
import com.example.dragdropxml.ui.ListFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager = binding.viewPager
        val listFragment = ArrayList<Fragment>()
        listFragment.add(ListFragment.newInstance())
        listFragment.add(BoardFragment.newInstance())
        val adapter = ScreenSlidePagerAdapter(this, listFragment)
        viewPager.adapter = adapter
        TabLayoutMediator(binding.tablayout, viewPager) { tab, position ->
            when (position) {

            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.container.id, fragment, "fragment").commit()
    }


    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle
            // the Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }

    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity, val list: List<Fragment>) :
        FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 2
        override fun createFragment(position: Int): Fragment = list[position]
    }
}
