package com.example.healthapp.trainConstructor.ui.exercises

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.healthapp.databinding.LongExerciseViewItemBinding
import com.example.healthapp.trainConstructor.domain.entities.Exercise

class ExerciseAdapter() : ListAdapter<Exercise, ExerciseAdapter.ViewHolder>(DiffCallback()) {

    private var binding: LongExerciseViewItemBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = LongExerciseViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: LongExerciseViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise) {
            with(binding) {
                approachesView.data = exercise.approaches
                name.text = exercise.name
                duration.text = exercise.duration.toString()
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Exercise>() {

        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.name == newItem.name && oldItem.approaches.size == newItem.approaches.size
        }
    }
}