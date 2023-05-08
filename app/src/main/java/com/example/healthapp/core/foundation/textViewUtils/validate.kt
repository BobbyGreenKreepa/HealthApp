package com.example.healthapp.core.foundation.textViewUtils

import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import com.example.healthapp.R
import com.example.healthapp.core.foundation.recyclerView.clearError
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.emptyValidate(errorText: String? = "Это обязательное поле!") {
    val isValid = this.editText?.text?.isNotEmpty() ?: false

    if (!isValid) {
        this.error = errorText
    } else {
        this.error = null
    }
}

fun TextView.setErrorTextColor() {
    this.setTextColor(ResourcesCompat.getColor(this.resources, R.color.error_color, null))
}