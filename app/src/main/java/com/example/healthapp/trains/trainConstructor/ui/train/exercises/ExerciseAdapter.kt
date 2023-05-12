package com.example.healthapp.trains.trainConstructor.ui.train.exercises

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.core.foundation.viewUtils.expandOrCollapse
import com.example.healthapp.core.ui.kit.SimpleApproachInfoView
import com.example.healthapp.databinding.LongExerciseViewItemBinding

class ExerciseAdapter(private val transition: () -> Unit) :
    ListAdapter<ExerciseListItem, ExerciseAdapter.ViewHolder>(DiffCallback()) {

    private lateinit var binding: LongExerciseViewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = LongExerciseViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = getItem(position)
        holder.bind(listItem)

        holder.itemView.setOnLongClickListener {
            listItem.expanded = !listItem.expanded

            holder.approachesContainer.removeAllViews()
            listItem.value.approaches.forEach {
                holder.approachesContainer.addView(SimpleApproachInfoView(holder.itemView.context, it))
            }

            holder.approachesContainer.expandOrCollapse(listItem.expanded)

            return@setOnLongClickListener true
        }
    }

    inner class ViewHolder(private val binding: LongExerciseViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val approachesContainer = binding.approachesContainer

        fun bind(exercise: ExerciseListItem) {
            with(exercise.value) {
                binding.name.text = name
                binding.duration.text = duration.toString()
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<ExerciseListItem>() {

        override fun areItemsTheSame(oldItem: ExerciseListItem, newItem: ExerciseListItem): Boolean {
            return oldItem.value.uid == newItem.value.uid
        }

        override fun areContentsTheSame(oldItem: ExerciseListItem, newItem: ExerciseListItem): Boolean {
            return oldItem.value.name == newItem.value.name
                    && oldItem.value.approaches.size == newItem.value.approaches.size
        }
    }
}