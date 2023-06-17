package com.example.healthapp.home.ui.trainsList

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.databinding.TrainListItemBinding
import com.example.healthapp.trainConstructor.domain.entities.Train

class TrainsAdapter (
    private val onItemClick: (Train) -> Unit,
    private val onTrainPlayClick: (Train) -> Unit,
    ) : ListAdapter<Train, TrainsAdapter.ViewHolder>(DiffCallback()) {

    private lateinit var binding: TrainListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = TrainListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    inner class ViewHolder(private val binding: TrainListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(train: Train, onItemClick: (Train) -> Unit) {
            binding.apply {
                name.text = train.name
                date.text = train.dateTime

                var approachesCount = 0

                train.exercises.forEach {
                    approachesCount += it.approaches.size
                }

                description.text = getDescriptionText(train, root.resources)

                description.setOnClickListener {
                    onItemClick(train)
                }

                start.setOnClickListener {
                    onTrainPlayClick.invoke(train)
                }
            }
        }

        private fun getDescriptionText(train: Train, resources: Resources) : String {
            val exercises = resources.getQuantityString(R.plurals.exercises_plurals, train.exercises.size, train.exercises.size)

            var approachesCount = 0

            train.exercises.forEach {
                approachesCount += it.approaches.size
            }

            val approaches = resources.getQuantityString(R.plurals.approaches_plurals, approachesCount, approachesCount)
            val minutes = resources.getQuantityString(R.plurals.minutes_plurals, 1, 1)

            return resources.getString(R.string.train_description_format, exercises, approaches, minutes)
        }
    }

    private class DiffCallback() : DiffUtil.ItemCallback<Train>() {
        override fun areItemsTheSame(oldItem: Train, newItem: Train): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Train, newItem: Train): Boolean {
            return oldItem == newItem
        }

    }
}