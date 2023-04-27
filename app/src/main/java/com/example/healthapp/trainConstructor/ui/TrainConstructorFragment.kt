package com.example.healthapp.trainConstructor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.healthapp.core.foundation.textViewUtils.onRedactFinish
import com.example.healthapp.core.foundation.textViewUtils.onTextSet
import com.example.healthapp.databinding.FragmentTrainConstructorBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.FlowCollector

@AndroidEntryPoint
class TrainConstructorFragment : Fragment() {

    private var _binding: FragmentTrainConstructorBinding? = null
    private val  binding get() = _binding!!

    private val viewModel: TrainConstructorViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTrainConstructorBinding.inflate(inflater, container, false)
        binding.addTrain.setOnClickListener { viewModel.addTrain() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observerViewState() {
        viewModel.viewState.collect( {train -> viewModel.} )
    }

    private fun observeViews() {
        with(binding) {
            trainName.onRedactFinish { viewModel.trainName.value = it }
            trainDate.onTextSet { viewModel.trainDate.value = it }
        }
    }
}