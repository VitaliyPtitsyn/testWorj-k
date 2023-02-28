package com.example.presentation.screens.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.presentation.R
import com.example.presentation.base.BaseMVVMFragment
import com.example.presentation.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseMVVMFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun attachViewModels(
        binding: FragmentMainBinding,
        savedInstanceState: Bundle?
    ) {
        binding.vm = viewModel
    }

}