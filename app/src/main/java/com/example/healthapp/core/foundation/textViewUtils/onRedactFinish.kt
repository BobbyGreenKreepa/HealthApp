package com.example.healthapp.core.foundation.textViewUtils

import android.text.Editable
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener

fun AppCompatEditText.onRedactFinish(listener: (String) -> Unit) {
    this.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) listener.invoke(this.text.toString())
    }
}

fun AppCompatEditText.onTextSet(listener: (String) -> Unit) {
    this.addTextChangedListener { text: Editable? -> listener.invoke(text.toString()) }
}
