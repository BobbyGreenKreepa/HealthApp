package com.example.healthapp.core.ui.fragementUtils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(text: String) {
    val toast = Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT)
    toast.show()
}