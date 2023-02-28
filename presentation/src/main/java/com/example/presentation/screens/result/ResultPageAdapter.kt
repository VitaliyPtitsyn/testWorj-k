package com.example.presentation.screens.result

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.presentation.screens.result.chart.ChartFragment
import com.example.presentation.screens.result.table.TableFragment

class ResultPageAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        PAGE_CHARTS -> ChartFragment.newInstance()
        PAGE_TABLE -> TableFragment.newInstance()
        else -> throw java.lang.RuntimeException("Unexpected page count")
    }


    companion object {
        val PAGE_COUNT = 2
        val PAGE_CHARTS = 0
        val PAGE_TABLE = 1
    }

}