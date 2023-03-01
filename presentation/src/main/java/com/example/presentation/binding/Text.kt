package com.example.presentation.binding

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.domain.models.StatPoint
import com.example.presentation.R
import com.example.presentation.errorHadling.InvalidInputError
import com.example.presentation.screens.result.table.TablePointsTypes
import com.example.presentation.tools.InputFilterMinMax
import com.example.presentation.tools.SpanFormatter
import com.example.presentation.tools.style

@BindingAdapter(value = ["minRange", "maxRange"])
fun EditText.setRange(min: Int?, max: Int?) {
    if (min != null && max != null) {
        filters = arrayOf(InputFilterMinMax(min, max))
    } else {
        filters = emptyArray()
    }
}

@BindingAdapter("general_error")
internal fun TextView.errorDisplay(e: java.lang.Exception?) {
    if (e == null) return

    text = when(e){
        is InvalidInputError -> context.getString(R.string.input_error)
        else ->  context.getString(R.string.smth_when_wrong)
    }
}

@BindingAdapter("xAxis")
internal fun TextView.xAxis(point: TablePointsTypes.PointsRaw?) {
    if (point == null) return

    val xAxis = point.point.x.toString().style(context, R.style.AppTheme_textStyle_SelectedRow)
    text = SpanFormatter.format(context.getString(R.string.x_axis_format), xAxis)
}

@BindingAdapter("yAxis")
internal fun TextView.yAxis(point: TablePointsTypes.PointsRaw?) {
    if (point == null) return

    val yAxis = point.point.y.toString().style(context, R.style.AppTheme_textStyle_SelectedRow)
    text = SpanFormatter.format(context.getString(R.string.y_axis_format), yAxis)
}

@BindingAdapter("statPointOrder")
internal fun TextView.order(point: TablePointsTypes.PointsRaw?) {
    if (point == null) return

    val order = point.order.toString().style(context, R.style.AppTheme_textStyle_SelectedRow)
    text = SpanFormatter.format(context.getString(R.string.y_axis_format), order)
}

@BindingAdapter("rangeHeader")
internal fun TextView.range(point: TablePointsTypes.RangeHeader?) {
    if (point == null) return

    val xAxis = point.firstPoint.x.toString().style(context, R.style.AppTheme_textStyle_SelectedRow)
    val xAxisEnd = point.endPoint.y.toString().style(context, R.style.AppTheme_textStyle_SelectedRow)

    text = SpanFormatter.format(context.getString(R.string.range_title_format), xAxis, xAxisEnd)
}

@BindingAdapter(value = ["minRange", "maxRange"])
fun TextView.setRawHeader(min: Int?, max: Int?) {
    if (min != null && max != null) {
        filters = arrayOf(InputFilterMinMax(min, max))
    } else {
        filters = emptyArray()
    }
}
