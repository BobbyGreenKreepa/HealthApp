package com.example.healthapp.trainView.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieDrawable
import com.example.healthapp.databinding.FragmentTrainViewBinding

class TrainViewFragment : Fragment() {

    private var _binding: FragmentTrainViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrainViewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.animationView.setAnimation("anims/TrainAnimation(Green).json")
        binding.animationView.playAnimation()
        binding.animationView.repeatMode = LottieDrawable.REVERSE
        binding.animationView.repeatCount = LottieDrawable.INFINITE
    }
}
