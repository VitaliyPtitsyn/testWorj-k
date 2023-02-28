package com.example.presentation.screens.result

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.presentation.R
import com.example.presentation.base.BaseMVVMFragment
import com.example.presentation.databinding.FragmentMainBinding
import com.example.presentation.databinding.FragmentResultBinding
import com.example.presentation.screens.main.MainViewModel
import com.example.presentation.screens.result.ResultPageAdapter.Companion.PAGE_CHARTS
import com.example.presentation.screens.result.ResultPageAdapter.Companion.PAGE_TABLE
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentResult : BaseMVVMFragment<FragmentResultBinding>(R.layout.fragment_result) {

    val viewModel: ResultViewModel by viewModels()

    override fun attachViewModels(
        binding: FragmentResultBinding,
        savedInstanceState: Bundle?
    ) {
        if (binding.tabLayout != null && binding.pager != null)
            initViewPagging(binding.tabLayout, binding.pager)
    }

    private fun initViewPagging(tabLayout: TabLayout, viewPager: ViewPager2) {
        val adapter = ResultPageAdapter(this)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                PAGE_CHARTS -> requireContext().getString(R.string.charts)
                PAGE_TABLE -> requireContext().getString(R.string.table)
                else -> ""
            }
        }.attach()

    }

    companion object {
        val ID = R.id.result_fragment
    }
}