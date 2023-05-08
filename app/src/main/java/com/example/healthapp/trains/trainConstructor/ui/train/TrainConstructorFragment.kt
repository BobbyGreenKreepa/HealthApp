package com.example.healthapp.trains.trainConstructor.ui.train

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.healthapp.R
import com.example.healthapp.core.foundation.coroutines.launchOnStart
import com.example.healthapp.core.foundation.recyclerView.clearError
import com.example.healthapp.core.foundation.recyclerView.setError
import com.example.healthapp.core.foundation.textViewUtils.*
import com.example.healthapp.core.ui.fragementUtils.showToast
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observerViewState()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initButtons(adapter: ExerciseAdapter) {
        binding.apply {
            addExercise.setOnClickListener {
                findNavController().navigate(R.id.action_trainConstructor_to_addExerciseSharedFragment)
            }

            addTrain.setOnClickListener {
                viewModel.validateTrain { validate ->
                    if (validate) {
                        viewModel.addTrain()
                        findNavController().popBackStack()
                    } else {
                        trainDateLayout.emptyValidate()
                        trainNameLayout.emptyValidate()
                        if (adapter.currentList.isEmpty()) {
                            showToast(getString(R.string.add_train_validate_failed_text))
                        }
                    }
                }
            }
        }
    }

    private fun observerViewState() {
        val adapter = ExerciseAdapter {
            TransitionManager.beginDelayedTransition(binding.exercisesList, AutoTransition())
        }

        initButtons(adapter)

        binding.apply {
            exercisesList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            exercisesList.adapter = adapter
            exercisesList.setHasFixedSize(true)

            trainNameLayout.onTextChange { viewModel.trainName.value = it }
            trainDate.onTextSet { viewModel.trainDate.value = it }
        }

        viewModel.exercises.onEach {
            binding.emptyWarning.isVisible = it.isEmpty()
            adapter.submitList(it)
        }.launchOnStart(lifecycleScope)
    }
}