package com.example.healthapp.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.healthapp.R
import com.example.healthapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        init()

        return binding.root
    }

    private fun init() {
        binding.addJogButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_jogFragment)
        }

        binding.addTrainButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_navigation3)
        }
    }
}