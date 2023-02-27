package com.example.presentation.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.presentation.uiModels.NavBottomAppState

@BindingAdapter("bottomState")
fun FloatingActionButton.bindNavBottomApp(navBottomAppState: NavBottomAppState?) {
    if (navBottomAppState == null) {
        hide()
        return
    }
    if (navBottomAppState.fabBtnModel == null) {
        hide()
    } else {
        show()
        setImageResource(navBottomAppState.fabBtnModel.imgResId)
    }
}
