package com.example.healthapp.core.foundation.textViewUtils

import android.text.Editable
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener

fun AppCompatEditText.onTextChange(listener: (String) -> Unit) {
    this.addTextChangedListener { text -> listener.invoke(text.toString()) }
}

fun AppCompatEditText.onTextSet(listener: (String) -> Unit) {
    this.addTextChangedListener { text: Editable? -> listener.invoke(text.toString()) }
}
