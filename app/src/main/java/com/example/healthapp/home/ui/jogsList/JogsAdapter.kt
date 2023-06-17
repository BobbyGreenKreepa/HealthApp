package com.example.healthapp.home.ui.jogsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.healthapp.R
import com.example.healthapp.core.foundation.map.YandexURLFormatter
import com.example.healthapp.databinding.JogListItemBinding
import com.example.healthapp.jogConstructor.domain.entities.Jog

class JogsAdapter(
    private val onItemClick: (Jog) -> Unit,
    private val urlFormatter: YandexURLFormatter
    ) : ListAdapter<Jog, JogsAdapter.ViewHolder>(DiffCallback()) {

    private lateinit var binding: JogListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = JogListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, urlFormatter)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: JogListItemBinding, private val urlFormatter: YandexURLFormatter) : RecyclerView.ViewHolder(binding.root){

        fun bind(jog: Jog) {
            binding.apply {
                Glide.with(trailPhoto)
                    .load(urlFormatter.format(jog.points))
                    .placeholder(R.drawable.calendar_ic)
                    .fitCenter()
                    .into(trailPhoto)

                name.text = jog.name
                distance.text = distance.resources.getString(R.string.format_distance, jog.distance)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Jog>() {
        override fun areItemsTheSame(oldItem: Jog, newItem: Jog): Boolean {
            return oldItem.id == newItem.id;
        }

        override fun areContentsTheSame(oldItem: Jog, newItem: Jog): Boolean {
            return oldItem == newItem
        }
    }
}
