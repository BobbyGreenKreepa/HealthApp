package com.example.healthapp.core.ui.kit

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.healthapp.R
import com.example.healthapp.databinding.SimpleApproachInfoViewBinding
import com.example.healthapp.trains.trainConstructor.domain.entities.Approach

@SuppressLint("ViewConstructor")
class SimpleApproachInfoView constructor(
    context: Context,
    private val approach: Approach
) : ConstraintLayout(context, null, 0) {

    private val binding: SimpleApproachInfoViewBinding


    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.simple_approach_info_view, this, true)
        binding = SimpleApproachInfoViewBinding.bind(this)
        setValues()
    }

    private fun setValues() {
        with(binding) {
            duration.text = approach.duration.toString()

            if (approach.repeats == 0) {
                repeats.visibility = GONE
            } else {
                repeats.text = approach.repeats.toString()
            }

            complexity.text = approach.complexity
            index.text = approach.index.toString()
        }
    }
}