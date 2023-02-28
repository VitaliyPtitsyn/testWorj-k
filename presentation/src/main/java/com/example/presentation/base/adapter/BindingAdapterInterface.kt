package com.example.presentation.base.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

interface BindingAdapterInterface {
    @LayoutRes
    fun layoutId(viewType: Int): Int

    fun onBindingCreated(binding: ViewDataBinding, @LayoutRes layoutId: Int) = Unit

    fun onBind(binding: ViewDataBinding, position: Int) = Unit

    fun onBind(binding: ViewDataBinding, position: Int, payloads: MutableList<Any>) = Unit

    fun onAttachToWindow(binding: ViewDataBinding, position: Int) = Unit

    fun onDetachFromWindow(binding: ViewDataBinding) = Unit

    fun onUnbind(binding: ViewDataBinding) = Unit
}