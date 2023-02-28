package com.example.presentation.base.adapter

import androidx.lifecycle.Lifecycle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.cancel

open class BindingHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    private var lifecycleOwner: DataBindingLifecycleOwner? = null

    fun initLifecycleOwner() {
        binding.lifecycleOwner?.lifecycleScope?.cancel()
        binding.lifecycleOwner = DataBindingLifecycleOwner().apply {
            lifecycleOwner = this
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        }
    }

    fun handleLifecycleEvent(event: Lifecycle.Event) {
        lifecycleOwner?.lifecycleRegistry?.handleLifecycleEvent(event)
    }
}