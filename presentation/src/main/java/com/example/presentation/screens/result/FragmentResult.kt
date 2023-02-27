package com.example.presentation.screens.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.presentation.R
import com.example.presentation.base.BaseMVVMFragment
import com.example.presentation.databinding.FragmentMainBinding
import com.example.presentation.databinding.FragmentResultBinding
import com.example.presentation.screens.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentResult : BaseMVVMFragment<FragmentResultBinding>(R.layout.fragment_result) {


    val viewModel: ResultViewModel by viewModels()

    override fun attachViewModels(
        binding: FragmentResultBinding,
        savedInstanceState: Bundle?
    ) {

    }


}