package com.example.presentation.base.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BindingAdapter(
    private val inflater: LayoutInflater
) : RecyclerView.Adapter<BindingHolder>(), BindingAdapterInterface {

    protected val controller = BindingAdapterController(this)

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder =
        controller.onCreateViewHolder(inflater(viewType), parent, layoutId(viewType))

    final override fun onBindViewHolder(holder: BindingHolder, position: Int) = controller.onBindViewHolder(holder, position)

    final override fun onViewAttachedToWindow(holder: BindingHolder) = controller.onViewAttachedToWindow(holder)

    final override fun onViewDetachedFromWindow(holder: BindingHolder) = controller.onViewDetachedFromWindow(holder)

    final override fun onViewRecycled(holder: BindingHolder) = controller.onViewRecycled(holder)

    final override fun getItemViewType(position: Int): Int = viewType(position)

    open fun viewType(position: Int): Int = 0

    open fun inflater(viewType: Int): LayoutInflater = inflater
}