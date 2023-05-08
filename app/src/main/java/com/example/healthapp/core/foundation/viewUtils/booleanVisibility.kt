package com.example.healthapp.core.foundation.viewUtils

import android.view.View

fun booleanVisibility(visible: Boolean) = if (visible) View.VISIBLE else View.GONE

fun View.setBooleanVisibility(visible: Boolean) {
    if (visible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}