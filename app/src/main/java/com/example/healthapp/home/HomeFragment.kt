package com.example.healthapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return binding.root
    }
}