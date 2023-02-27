package com.core.presentation.base.screens.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseMVVMActivity<T : ViewDataBinding>(
    @LayoutRes open val layoutId: Int
) :  AppCompatActivity() {


    private var _binding: T? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        attachViewModels(binding)
    }

    abstract fun attachViewModels(binding: T)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}