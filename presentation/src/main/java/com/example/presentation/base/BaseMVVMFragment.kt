package com.example.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseMVVMFragment<T : ViewDataBinding>(
    @LayoutRes open val layoutId: Int
) : Fragment() {

    private var _binding: T? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false, DataBindingUtil.getDefaultComponent())
        attachViewModels(binding, savedInstanceState)
        binding.lifecycleOwner = this
        return binding.root
    }


    abstract fun attachViewModels(binding: T, savedInstanceState: Bundle?)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}