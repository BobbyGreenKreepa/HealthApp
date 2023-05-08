package com.example.healthapp.trains.trainConstructor.ui.approach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.healthapp.R
import com.example.healthapp.core.foundation.recyclerView.clearError
import com.example.healthapp.core.foundation.textViewUtils.emptyValidate
import com.example.healthapp.core.foundation.textViewUtils.onTextChange
import com.example.healthapp.core.ui.fragementUtils.showToast
import com.example.healthapp.databinding.FragmentAddAproachSharedDialogBinding
import com.example.healthapp.trains.trainConstructor.ui.TrainConstructorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddApproachSharedDialog : DialogFragment() {

    private val viewModel: TrainConstructorViewModel by hiltNavGraphViewModels(R.id.navigation3)

    private var _binding: FragmentAddAproachSharedDialogBinding? = null
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddAproachSharedDialogBinding.inflate(inflater, container, false)
        observeViewState()
        dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_background_rounded)
        return binding.root
    }

    private fun observeViewState() {
        with(binding) {
            index.text = root.context.getString(R.string.approach_index_format, viewModel.currentExerciseApproaches.value.size + 1)
            complexityLayout.onTextChange { viewModel.currentApproachComplexity.value = it }
            durationLayout.onTextChange { viewModel.currentApproachDuration.value = it }
            repeatsLayout.onTextChange { viewModel.currentApproachRepeats.value = it }

            save.setOnClickListener {
                viewModel.validateApproach { validate ->
                    if (validate) {
                        viewModel.addApproach()
                        dismiss()
                    } else {
                        complexityLayout.emptyValidate()
                        durationLayout.emptyValidate()
                    }
                }
            }
        }
    }
}