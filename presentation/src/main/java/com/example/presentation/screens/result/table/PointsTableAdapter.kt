package com.example.presentation.screens.result.table

import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.presentation.R
import com.example.presentation.base.adapter.BindingAdapter
import com.example.presentation.databinding.VhPointsBinding
import com.example.presentation.databinding.VhPointsSectionHeaderBinding

class PointsTableAdapter(
    inflater: LayoutInflater,
) : BindingAdapter(inflater) {

    private val asyncListDiffer: AsyncListDiffer<TablePointsTypes> =
        AsyncListDiffer(this, LiveLeaderboardDff)

    private val dataList: List<TablePointsTypes>
        get() = asyncListDiffer.currentList

    override fun getItemCount() = dataList.size

    override fun layoutId(viewType: Int): Int = when (viewType) {
        VIEW_TYPE_ITEM -> R.layout.vh_points
        VIEW_TYPE_HEADER -> R.layout.vh_points_section_header
        VIEW_TYPE_FIRST_HEADER -> R.layout.vh_points_header
        else -> error("unimplemented")
    }

    override fun viewType(position: Int) = dataList[position].toViewType()

    fun submitUpdate(update: List<TablePointsTypes>) {
        asyncListDiffer.submitList(update)
    }

    override fun onBind(binding: ViewDataBinding, position: Int) {
        when (binding) {
            is VhPointsBinding -> {
                (dataList[position] as? TablePointsTypes.PointsRaw)?.let {
                    binding.pointRaw = it
                }
            }
            is VhPointsSectionHeaderBinding -> {
                (dataList[position] as? TablePointsTypes.RangeHeader)?.let {
                    binding.rangeHeader = it
                }
            }
        }
    }

    private fun TablePointsTypes.toViewType() = when (this) {
        TablePointsTypes.FirstHeader -> VIEW_TYPE_FIRST_HEADER
        is TablePointsTypes.PointsRaw -> VIEW_TYPE_ITEM
        is TablePointsTypes.RangeHeader -> VIEW_TYPE_HEADER
    }

    object LiveLeaderboardDff : DiffUtil.ItemCallback<TablePointsTypes>() {

        override fun areItemsTheSame(
            oldItem: TablePointsTypes,
            newItem: TablePointsTypes
        ): Boolean {
            return if (oldItem.javaClass == newItem.javaClass)
                when (oldItem) {
                    TablePointsTypes.FirstHeader -> true
                    is TablePointsTypes.PointsRaw -> oldItem.hashCode() == newItem.hashCode()
                    is TablePointsTypes.RangeHeader -> oldItem.hashCode() == newItem.hashCode()
                }
            else return false
        }

        override fun areContentsTheSame(
            oldItem: TablePointsTypes,
            newItem: TablePointsTypes
        ): Boolean {
            return when (oldItem) {
                TablePointsTypes.FirstHeader -> true
                is TablePointsTypes.PointsRaw -> oldItem == newItem
                is TablePointsTypes.RangeHeader -> oldItem == newItem
            }
        }
    }


    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_HEADER = 2
        const val VIEW_TYPE_FIRST_HEADER = 3
    }
}