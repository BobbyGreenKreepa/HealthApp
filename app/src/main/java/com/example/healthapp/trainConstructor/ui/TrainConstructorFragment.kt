package com.example.healthapp.trainConstructor.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthapp.R
import com.example.healthapp.databinding.FragmentHomeBinding
import com.example.healthapp.databinding.FragmentTrainConstructorBinding

class TrainConstructorFragment : Fragment() {

    private var _binding: FragmentTrainConstructorBinding? = null
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTrainConstructorBinding.inflate(inflater, container, false)
        return binding.root
    }
}