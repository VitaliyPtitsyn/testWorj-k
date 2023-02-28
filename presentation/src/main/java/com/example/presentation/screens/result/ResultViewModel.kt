package com.example.presentation.screens.result

import android.graphics.Color
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.domain.models.StatisticsPoints
import com.example.presentation.base.BaseViewModel
import com.example.presentation.navigation.NavigationHandler
import com.example.presentation.screens.result.table.TablePointsTypes
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class ResultViewModel @AssistedInject constructor(
    navigationHandler: NavigationHandler,
    @Assisted private val saveHandle: SavedStateHandle,
    @Assisted private val arg: FragmentResultArgs
) : BaseViewModel(
    navigationHandler
) {

    @AssistedFactory
    interface Factory {
        fun create(
            saveHandle: SavedStateHandle,
            arg: FragmentResultArgs,
        ): ResultViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            owner: SavedStateRegistryOwner,
            arg: FragmentResultArgs,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, null) {

                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return assistedFactory.create(handle, arg) as T
                }
            }
    }

    val pointsData = MutableLiveData<List<TablePointsTypes>>().apply {
        viewModelScope.launch {
            postValue(arg.points.toTablePointsTypes())
        }
    }

    val pointsChartData = MutableLiveData<LineData>().apply {
        viewModelScope.launch {
            postValue(arg.points.toChartDataPointsTypes())
        }
    }

}

private fun StatisticsPoints.toChartDataPointsTypes(): LineData {

    // create a dataset and give it a type
    val set1 = LineDataSet(points.map {
        Entry(it.x, it.y)
    }, "DataSet").apply {
        setMode(LineDataSet.Mode.CUBIC_BEZIER)
        setCubicIntensity(0.2f)
        setDrawFilled(true)
        setDrawCircles(false)
        setLineWidth(1.8f)
        setCircleRadius(4f)
        setCircleColor(Color.WHITE)
        setHighLightColor(Color.rgb(244, 117, 117))
        setColor(Color.WHITE)
        setFillColor(Color.WHITE)
        setFillAlpha(100)
        setDrawHorizontalHighlightIndicator(false)
    }
    // create a data object with the data sets
    return LineData(set1).apply {
//        setValueTypeface(tfLight)
        setValueTextSize(9f)
        setDrawValues(true)
    }
}

private fun StatisticsPoints.toTablePointsTypes(): List<TablePointsTypes>? {
    val resultList = ArrayList<TablePointsTypes>()
    resultList.add(TablePointsTypes.FirstHeader)

    val step = 10
    points.forEachIndexed { index, item ->
        if (index != 0 && index % step == 0)
            resultList.add(TablePointsTypes.RangeHeader(points[index - step], points[index]))
        resultList.add(TablePointsTypes.PointsRaw(index + 1, points[index]))
    }
    return resultList
}
