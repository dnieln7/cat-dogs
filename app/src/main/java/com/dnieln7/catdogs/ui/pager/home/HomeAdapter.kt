package com.dnieln7.catdogs.ui.pager.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dnieln7.catdogs.ui.cat.CatsFragment
import com.dnieln7.catdogs.ui.dog.DogsFragment

class HomeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabsCreator: Map<Int, () -> Fragment> = mapOf(
        CAT_PAGE_INDEX to { CatsFragment() },
        DOG_PAGE_INDEX to { DogsFragment() }
    )

    override fun getItemCount() = tabsCreator.size

    override fun createFragment(position: Int): Fragment {
        return tabsCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

    companion object {
        const val CAT_PAGE_INDEX = 0
        const val DOG_PAGE_INDEX = 1
    }
}