package com.example.healthapp.core.foundation.recyclerView

import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.healthapp.R

fun View.setError() {
    this.background = ResourcesCompat.getDrawable(this.context.resources, R.drawable.default_rounded_corners_with_error_bg, null)
}

fun View.clearError() {
    this.background = ResourcesCompat.getDrawable(this.context.resources, R.drawable.default_rounded_corners_bg, null)
}
