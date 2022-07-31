package com.mustafahasria.medicus.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mustafahasria.medicus.R
import com.mustafahasria.medicus.databinding.ItemRecyclerReportBinding
import com.mustafahasria.medicus.model.medical.domain.Report

class ReportAdapter(
    private var reports: List<Report>,
    private val clickListener: (Report, Int) -> Unit,
) : ListAdapter<Report, ReportAdapter.ReportViewHolder>(COMPARATOR) {

    //region Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding =
            ItemRecyclerReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bind(reports[holder.layoutPosition])
        setAnimation(holder.itemView)
    }

    override fun getItemCount(): Int {
        return reports.size
    }
    //endregion

    //region Helper
    private fun setAnimation(viewToAnimate: View) {
        if (viewToAnimate.animation == null) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.anim_fade_in)
            viewToAnimate.animation = animation
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Report>() {
            override fun areItemsTheSame(oldItem: Report, newItem: Report) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Report, newItem: Report) =
                oldItem.id == newItem.id
        }
    }

    fun setData(reports: List<Report>) {
        this.reports = reports
        notifyDataSetChanged()
    }
    //endregion

    //region View holder
    class ReportViewHolder(
        val binding: ItemRecyclerReportBinding,
        private val clickListener: (Report, Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemEntity: Report) {
            binding.apply {
                root.setOnClickListener {
                    clickListener(itemEntity, layoutPosition)
                }
                itemRecyclerReportTitle.text = itemEntity.symbol
                itemRecyclerReportDate.text = itemEntity.date
                itemRecyclerReportValue.text = itemEntity.value.toString()
                if (itemEntity.color == "#E64A19") {
                    itemRecyclerReportTitle.setBackgroundResource(R.drawable.blue_circular_card)
                    itemRecyclerReportTitle.setTextColor("#00BFD4".toColorInt())
                } else {
                    itemRecyclerReportTitle.setBackgroundResource(R.drawable.red_circular_card)
                }
            }
        }
    }
    //endregion
}