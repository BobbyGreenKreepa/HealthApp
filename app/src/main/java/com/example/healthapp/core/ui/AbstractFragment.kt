package com.example.healthapp.core.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.healthapp.core.MainActivity

abstract class AbstractFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).title = getTitle()
    }

    abstract fun getTitle(): String
}