package com.dnieln7.catdogs.ui.pager.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dnieln7.catdogs.R
import com.dnieln7.catdogs.databinding.FragmentHomeViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager2 = binding.pager

        viewPager2.adapter = HomeAdapter(this)

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.setText(getTabTitle(position))
        }.attach()

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            HomeAdapter.CAT_PAGE_INDEX -> R.drawable.ic_cat
            HomeAdapter.DOG_PAGE_INDEX -> R.drawable.ic_dog
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): Int {
        return when (position) {
            HomeAdapter.CAT_PAGE_INDEX -> R.string.tab_cats
            HomeAdapter.DOG_PAGE_INDEX -> R.string.tab_dogs
            else -> throw IndexOutOfBoundsException()
        }
    }
}