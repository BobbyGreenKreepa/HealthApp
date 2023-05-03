package com.example.healthapp.trains.trainConstructor.ui.approach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.healthapp.R
import com.example.healthapp.core.foundation.textViewUtils.onTextChange
import com.example.healthapp.databinding.FragmentAddAproachSharedDialogBinding
import com.example.healthapp.trains.trainConstructor.ui.TrainConstructorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddApproachSharedDialog : DialogFragment() {

    private val viewModel: TrainConstructorViewModel by hiltNavGraphViewModels(R.id.navigation3)

    private var _binding: FragmentAddAproachSharedDialogBinding? = null
    private val  binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddAproachSharedDialogBinding.inflate(inflater, container, false)
        observeViewState()
        dialog?.window?.setBackgroundDrawableResource(R.drawable.doalog_background_rounded)
        return binding.root
    }

    private fun observeViewState() {
        with(binding) {
            complexity.onTextChange { viewModel.currentApproachComplexity.value = it }
            duration.onTextChange { viewModel.currentApproachDuration.value = it }
            repeats.onTextChange { viewModel.currentApproachRepeats.value = it }
            save.setOnClickListener {
                viewModel.addApproach()
                dismiss()
            }
        }
    }
}