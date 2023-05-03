package com.example.healthapp.trains.trainConstructor.ui.train

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.healthapp.R
import com.example.healthapp.core.foundation.coroutines.launchOnStart
import com.example.healthapp.core.foundation.textViewUtils.onTextChange
import com.example.healthapp.core.foundation.textViewUtils.onTextSet
import com.example.healthapp.databinding.FragmentTrainConstructorBinding
import com.example.healthapp.trains.trainConstructor.ui.TrainConstructorViewModel
import com.example.healthapp.trains.trainConstructor.ui.train.exercises.ExerciseAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TrainConstructorFragment : Fragment() {

    private var _binding: FragmentTrainConstructorBinding? = null
    private val  binding get() = _binding!!

    private val viewModel: TrainConstructorViewModel by hiltNavGraphViewModels(R.id.navigation3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTrainConstructorBinding.inflate(inflater, container, false)
        binding.addTrain.setOnClickListener { viewModel.addTrain() }
        binding.addExercise.setOnClickListener { viewModel.addExercise() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observerViewState()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observerViewState() {
        val adapter = ExerciseAdapter {
            TransitionManager.beginDelayedTransition(binding.exercisesList, AutoTransition())
        }

        with(binding) {
            exercisesList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            exercisesList.adapter = adapter
            exercisesList.setHasFixedSize(true)
            trainName.onTextChange { viewModel.trainName.value = it }
            trainDate.onTextSet { viewModel.trainDate.value = it }

            addExercise.setOnClickListener {
                findNavController().navigate(R.id.action_trainConstructor_to_addExerciseSharedFragment)
            }
        }

        viewModel.exercises.onEach { adapter.submitList(it) }.launchOnStart(lifecycleScope)
    }
}