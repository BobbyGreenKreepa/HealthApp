package com.example.healthapp.home.ui.trainInfo

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.healthapp.R
import com.example.healthapp.core.ui.kit.animations.StringEvaluator
import com.example.healthapp.databinding.FragmentTrainInfoDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TrainInfoDialog : BottomSheetDialogFragment() {

    private val setTextAnimationDuration = 2000L

    private val stringEvaluator = StringEvaluator()

    private val args: TrainInfoDialogArgs by navArgs()

    private var _binding: FragmentTrainInfoDialogBinding? = null
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrainInfoDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val info = StringBuilder()
        val train = args.train
        val approachesInfo = StringBuilder()

        for (exercise in train.exercises) {

            exercise.approaches.forEach {
                approachesInfo.append(getString(R.string.approach_info_format,
                    it.index.substring(0, it.index.indexOf(' ')).toInt(),
                    it.complexity,
                    it.duration,
                    it.repeats)
                )
            }

            approachesInfo.append("\n")
        }

        info.append(getString(R.string.exercise_info_format, train.name, approachesInfo.toString()))

        info.append(info.toString())
        info.append(info.toString())
        info.append(info.toString())
        info.append(info.toString())
        info.append(info.toString())

        val animator = ValueAnimator
            .ofObject(stringEvaluator, "", info.toString())
            .apply {
                duration = setTextAnimationDuration
                addUpdateListener {
                    val animatedValue = it.animatedValue.toString()
                    binding.info.text = animatedValue
                }
            }

        animator.start()
    }
}