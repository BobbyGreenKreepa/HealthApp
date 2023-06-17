package com.example.healthapp.trainConstructor.ui.constructor.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthapp.R
import com.example.healthapp.core.foundation.coroutines.launchOnResumed
import com.example.healthapp.core.foundation.textViewUtils.emptyValidate
import com.example.healthapp.core.foundation.textViewUtils.onTextChange
import com.example.healthapp.core.ui.fragementUtils.showToast
import com.example.healthapp.databinding.FragmentAddExerciseSharedBinding
import com.example.healthapp.trainConstructor.domain.entities.Approach
import com.example.healthapp.trainConstructor.ui.constructor.TrainConstructorViewModel
import com.example.healthapp.trainConstructor.ui.constructor.exercise.approaches.ApproachesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ExerciseConstructorFragment : Fragment() {

    private val viewModel: TrainConstructorViewModel by hiltNavGraphViewModels(R.id.innerTrainConstructor)

    private var _binding: FragmentAddExerciseSharedBinding? = null
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddExerciseSharedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewState()
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel.clearNonConsistentExercise()
            findNavController().popBackStack()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeViewState() {
        val adapter = ApproachesAdapter()

        with(binding) {
            approachesList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            approachesList.adapter = adapter
            approachesList.setHasFixedSize(true)

            exerciseNameLayout.onTextChange { viewModel.currentExerciseName.value = it }

            addApproach.setOnClickListener {
                findNavController().navigate(R.id.action_addExerciseSharedFragment_to_addAproachSharedDialog)
            }

            saveExercise.setOnClickListener {
                viewModel.validateExercise { validate ->
                    if (validate) {
                        viewModel.addExercise()
                        findNavController().popBackStack()
                    } else {
                        exerciseNameLayout.emptyValidate()
                        if (adapter.currentList.isEmpty()) {
                            showToast(getString(R.string.add_exercise_validate_failed_text))
                        }
                    }
                }
            }
        }

        viewModel.currentExerciseApproaches.onEach {
            parseApproaches(it, adapter)
            binding.emptyWarning.isVisible = it.isEmpty()
        }.launchOnResumed(viewLifecycleOwner)
    }

    private fun parseApproaches(approaches: List<Approach>, adapter: ApproachesAdapter) {
        binding.emptyWarning.isVisible = approaches.isEmpty()
        adapter.submitList(approaches)
    }
}