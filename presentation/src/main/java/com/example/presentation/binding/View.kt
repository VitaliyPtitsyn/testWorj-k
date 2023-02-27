package com.example.presentation.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("goneVisibility")
fun View.goneVisible(isVisible: Boolean?) {
    this.visibility = if (isVisible == true) View.VISIBLE else View.GONE
}