package com.example.presentation.binding

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.presentation.tools.InputFilterMinMax

@BindingAdapter(value = ["minRange", "maxRange"])
fun EditText.setRange(min: Int?, max: Int?) {
    if (min != null && max != null) {
        filters = arrayOf(InputFilterMinMax(min, max))
    } else {
        filters = emptyArray()
    }
}
