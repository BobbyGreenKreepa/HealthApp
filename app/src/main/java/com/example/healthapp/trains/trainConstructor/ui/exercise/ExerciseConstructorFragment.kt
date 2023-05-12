package com.example.healthapp.trains.trainConstructor.ui.exercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthapp.R
import com.example.healthapp.core.foundation.textViewUtils.emptyValidate
import com.example.healthapp.core.foundation.textViewUtils.onTextChange
import com.example.healthapp.core.ui.fragementUtils.showToast
import com.example.healthapp.databinding.FragmentAddExerciseSharedBinding
import com.example.healthapp.trains.trainConstructor.domain.entities.Approach
import com.example.healthapp.trains.trainConstructor.ui.TrainConstructorViewModel
import com.example.healthapp.trains.trainConstructor.ui.exercise.approaches.ApproachesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExerciseConstructorFragment : Fragment() {

    private val viewModel: TrainConstructorViewModel by hiltNavGraphViewModels(R.id.navigation3)

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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) { // Trigger the flow and start listening for values.
                viewModel.currentExerciseApproaches.onEach {
                    parseApproaches(it, adapter)
                }.collect {
                    binding.emptyWarning.isVisible = it.isEmpty()
                }
            }
        }
    }

    private fun parseApproaches(approaches: List<Approach>, adapter: ApproachesAdapter) {
        binding.emptyWarning.isVisible = approaches.isEmpty()
        adapter.submitList(approaches)
    }
}