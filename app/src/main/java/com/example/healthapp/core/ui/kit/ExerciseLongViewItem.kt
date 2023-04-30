package com.example.healthapp.core.ui.kit

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.healthapp.R
import com.example.healthapp.databinding.LongExerciseViewItemBinding
import com.example.healthapp.trains.trainConstructor.domain.entities.Exercise

class ExerciseLongViewItem (context: Context) : FrameLayout(context) {

    private val binding: LongExerciseViewItemBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.long_exercise_view_item, this, true)
        binding = LongExerciseViewItemBinding.bind(this)
    }

    var exercise: Exercise? = null
        set(value) {
            field = value
            parseInfo()
        }

    public fun expandOrCollapse(expand: Boolean) {
        composeApproaches()
        binding.approachesContainer.visibility = if (expand) VISIBLE else GONE
    }

    private fun parseInfo() {
        with(exercise!!) {
            binding.duration.text = duration.toString()
            binding.name.text = name
            binding.approachesCounter.text = approaches.size.toString()
        }
    }

    private fun composeApproaches() {
        exercise!!.approaches.forEach {
            val view = SimpleApproachInfoView(context, it)
            binding.approachesContainer.addView(view)
        }
    }
}