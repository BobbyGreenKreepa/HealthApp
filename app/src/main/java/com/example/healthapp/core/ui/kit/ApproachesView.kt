package com.example.healthapp.core.ui.kit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.healthapp.R
import com.example.healthapp.databinding.ApproachesViewBinding
import com.example.healthapp.trains.trainConstructor.domain.entities.Approach

class ApproachesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding: ApproachesViewBinding

    var data: List<Approach> = emptyList()
        set(value) {
            field = value
            composeApproaches(field)
        }

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.approaches_view, this, true)
        binding = ApproachesViewBinding.bind(this)
        setup()
    }

    private fun setup() {
        binding.approachesCounter.setOnClickListener { expand() }
    }

    private fun composeApproaches(approaches: List<Approach>) {
        if (binding.approachesContainer.childCount != 0) {
            return
        }

        approaches.forEach {
            val view = SimpleApproachInfoView(context, it)
            binding.approachesContainer.addView(view)
        }
    }

    private fun expand() {
        val containerVisibility: Int = if (binding.approachesContainer.visibility == VISIBLE) GONE else VISIBLE

        binding.approachesContainer.visibility = containerVisibility
        TransitionManager.beginDelayedTransition(this, AutoTransition())
    }
}