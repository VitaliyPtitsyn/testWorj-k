package com.example.presentation.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle

class BindingAdapterController(private val bai: BindingAdapterInterface) {

    fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, layoutId: Int): BindingHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, false)
        bai.onBindingCreated(binding, layoutId)
        return BindingHolder(binding)
    }

    fun onBindViewHolder(holder: BindingHolder, position: Int) {
        with(holder) {
            initLifecycleOwner()
            if (binding.root.isAttachedToWindow) {
                handleLifecycleEvent(Lifecycle.Event.ON_START)
            }
            bai.onBind(binding, position)
            binding.executePendingBindings()
        }
    }

    fun onBindViewHolder(holder: BindingHolder, position: Int, payloads: MutableList<Any>) {
        with(holder) {
            initLifecycleOwner()
            if (binding.root.isAttachedToWindow) {
                handleLifecycleEvent(Lifecycle.Event.ON_START)
            }
            bai.onBind(binding, position, payloads)
            binding.executePendingBindings()
        }
    }

    fun onViewAttachedToWindow(holder: BindingHolder) {
        with(holder) {
            handleLifecycleEvent(Lifecycle.Event.ON_START)
            bai.onAttachToWindow(binding, absoluteAdapterPosition)
        }
    }

    fun onViewDetachedFromWindow(holder: BindingHolder) {
        with(holder) {
            handleLifecycleEvent(Lifecycle.Event.ON_STOP)
            bai.onDetachFromWindow(binding)
        }
    }

    fun onViewRecycled(holder: BindingHolder) {
        with(holder) {
            bai.onUnbind(binding)
            handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            binding.unbind()
        }
    }

    fun getItemViewType(position: Int): Int = bai.layoutId(position)
}