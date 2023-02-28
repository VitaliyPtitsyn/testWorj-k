package com.example.presentation.screens.result.chart

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.presentation.R
import com.example.presentation.base.BaseMVVMFragment
import com.example.presentation.databinding.FragmentChartBinding
import com.example.presentation.screens.result.FragmentResultArgs
import com.example.presentation.screens.result.ResultViewModel
import com.github.mikephil.charting.charts.LineChart
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChartFragment : BaseMVVMFragment<FragmentChartBinding>(R.layout.fragment_chart) {

    /**
     * A litle pice of magic and currentHostFragment founded
     */
    private val hostFragment by lazy {
        val host = requireActivity().supportFragmentManager.findFragmentById(R.id.main_navigation)
        host?.childFragmentManager?.findFragmentById(R.id.main_navigation)
            ?: throw java.lang.RuntimeException("has to be fragmetn with ")
    }

    private val arg by lazy { FragmentResultArgs.fromBundle(hostFragment.requireArguments()) }

    @Inject
    lateinit var factory: ResultViewModel.Factory

    private val viewModel: ResultViewModel by viewModels(
        ownerProducer = { hostFragment },
        factoryProducer = { ResultViewModel.provideFactory(factory, hostFragment, arg) }
    )

    override fun attachViewModels(binding: FragmentChartBinding, savedInstanceState: Bundle?) {

        setUpChart(binding.lineChart)

    }

    private fun setUpChart(chart: LineChart) {
        chart.setViewPortOffsets(0f, 0f, 0f, 0f)
        chart.setBackgroundColor(Color.rgb(104, 241, 175))

        // no description text
        chart.getDescription().setEnabled(false)

        // enable touch gestures
        chart.setTouchEnabled(true)

        // enable scaling and dragging
        chart.setDragEnabled(true)
        chart.setScaleEnabled(true)
        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(false)

        chart.legend.isEnabled = false
        chart.animateXY(2000, 2000)

        // don't forget to refresh the drawing
        chart.invalidate()

        viewModel.pointsChartData.observe(viewLifecycleOwner) { data ->
            // set data
            chart.setData(data)
        }
    }

    companion object {
        fun newInstance() = ChartFragment()
    }

}