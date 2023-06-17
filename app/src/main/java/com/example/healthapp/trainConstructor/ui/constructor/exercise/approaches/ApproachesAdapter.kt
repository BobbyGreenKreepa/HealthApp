package com.example.healthapp.trainConstructor.ui.constructor.exercise.approaches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.databinding.SimpleApproachInfoViewBinding
import com.example.healthapp.trainConstructor.domain.entities.Approach

class ApproachesAdapter : ListAdapter<Approach, ApproachesAdapter.ViewHolder>(DiffCallback()) {

    private lateinit var binding: SimpleApproachInfoViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = SimpleApproachInfoViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = getItem(position)
        holder.bind(listItem)
    }

    inner class ViewHolder(private val binding: SimpleApproachInfoViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(approach: Approach) {
            with(binding) {
                index.text = approach.index
                complexity.text = approach.complexity
                duration.text = approach.duration
                repeats.isVisible = approach.repeats.isNotEmpty()
                repeats.text = approach.repeats
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Approach>() {

        override fun areItemsTheSame(oldItem: Approach, newItem: Approach): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Approach, newItem: Approach): Boolean {
            return oldItem.index == newItem.index
        }
    }
}
