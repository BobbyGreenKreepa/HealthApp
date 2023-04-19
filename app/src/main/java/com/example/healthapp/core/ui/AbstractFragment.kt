package com.example.healthapp.core.ui

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class AbstractFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract fun getTitle(): String
}