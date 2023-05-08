package com.example.healthapp.core.foundation.textViewUtils

import android.text.Editable
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import com.example.healthapp.core.foundation.recyclerView.clearError
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.onTextChange(listener: (String) -> Unit) {
    editText?.addTextChangedListener {text ->
        error = null
        listener.invoke(text.toString())
    }
}

fun AppCompatEditText.onTextSet(listener: (String) -> Unit) {
    this.addTextChangedListener { text: Editable? -> listener.invoke(text.toString()) }
}
