package com.example.healthapp.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.healthapp.R
import com.example.healthapp.core.ui.AbstractFragment
import com.example.healthapp.databinding.FragmentHomeBinding

class HomeFragment : AbstractFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val  binding get() = _binding!!

    override fun getTitle(): String {
        return getString(R.string.home_title)
    }

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
            TODO()
        }

        binding.addTrainButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_navigation3)
        }
    }
}