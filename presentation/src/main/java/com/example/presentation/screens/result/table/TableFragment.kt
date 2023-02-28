package com.example.presentation.screens.result.table

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.base.BaseMVVMFragment
import com.example.presentation.databinding.FragmentPointsBinding
import com.example.presentation.screens.result.FragmentResult
import com.example.presentation.screens.result.FragmentResultArgs
import com.example.presentation.screens.result.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TableFragment : BaseMVVMFragment<FragmentPointsBinding>(R.layout.fragment_points) {

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

    override fun attachViewModels(binding: FragmentPointsBinding, savedInstanceState: Bundle?) {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = PointsTableAdapter(layoutInflater)
        viewModel.pointsData.observe(viewLifecycleOwner) { adapter.submitUpdate(it) }
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.adapter = adapter
    }

    companion object {
        fun newInstance() = TableFragment()
    }

}