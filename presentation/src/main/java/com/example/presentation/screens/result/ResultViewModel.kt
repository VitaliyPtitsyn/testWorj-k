package com.example.presentation.screens.result

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.domain.models.StatisticsPoints
import com.example.presentation.R
import com.example.presentation.base.BaseViewModel
import com.example.presentation.navigation.NavigationHandler
import com.example.presentation.navigation.Screens
import com.example.presentation.screens.result.table.TablePointsTypes
import com.example.presentation.uiModels.FabBtnModel
import com.example.presentation.uiModels.FabPosition
import com.example.presentation.uiModels.NavBottomAppState
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


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

    val state = NavBottomAppState(
        fabBtnModel = FabBtnModel(
            imgResId = R.drawable.ic_back_flip,
            FabPosition.RIGHT,
            action = this@ResultViewModel::navigateBack,
        ),
        isBarVisible = true
    )

    init {
        viewModelScope.launch {
            navigationHandler.postAppState(R.id.result_fragment, state)
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

    fun navigateBack() = viewModelScope.launch {
        navigationHandler.sendNavigation(Screens.NavigateBack())
    }

    fun saveImage(context: Context, image: Bitmap) = viewModelScope.launch(Dispatchers.IO) {
        val imagesFolder = File(context.getCacheDir(), "images")
        var uri: Uri? = null
        try {
            imagesFolder.mkdirs()
            val file = File(imagesFolder, "shared_image.png")
            val stream = FileOutputStream(file)
            image.compress(Bitmap.CompressFormat.PNG, 90, stream)
            stream.flush()
            stream.close()
            uri = FileProvider.getUriForFile(context, "com.example.myapp.fileprovider", file)
            navigationHandler.sendNavigation(Screens.ShareImage(uri))
        } catch (e: IOException) {
            Timber.d("IOException while trying to write file for sharing: " + e.message)
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
}
