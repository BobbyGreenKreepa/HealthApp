package com.example.healthapp.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthapp.R
import com.example.healthapp.core.foundation.coroutines.launchOnResumed
import com.example.healthapp.databinding.FragmentHomeBinding
import com.example.healthapp.home.ui.jogsList.JogsAdapter
import com.example.healthapp.home.ui.trainsList.TrainsAdapter
import com.example.healthapp.trainConstructor.domain.entities.Train
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        init()
        initJogsAdapter()
        initTrainsAdapter()
        return binding.root
    }

    private fun initTrainsAdapter() {
        val adapter = TrainsAdapter(onItemClick = {
            onTrainClick(it)
        }, onTrainPlayClick = {
            findNavController().navigate(R.id.action_homeFragment_to_trainView)
        })

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.trainsList.apply {
            this.adapter = adapter
            this.layoutManager = layoutManager
        }

        viewModel.trains.onEach {
            adapter.submitList(it)
        }.launchOnResumed(viewLifecycleOwner)
    }

    private fun initJogsAdapter() {
        val adapter = JogsAdapter({ }, viewModel.yandexURLFormatter)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.jogsList.apply {
            this.adapter = adapter
            this.layoutManager = layoutManager
        }

        viewModel.jogs.onEach {
            adapter.submitList(it)
        }.launchOnResumed(viewLifecycleOwner)
    }

    private fun init() {
        binding.apply {
            addJogButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_jogFragment)
            }

            addTrainButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_navigation3)
            }
        }
    }

    private fun onTrainClick(train: Train) {
        val action = HomeFragmentDirections.actionHomeFragmentToTrainInfoDialog(train)
        findNavController().navigate(action)
    }
}