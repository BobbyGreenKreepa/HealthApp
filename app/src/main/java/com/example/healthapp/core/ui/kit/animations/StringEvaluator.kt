package com.example.healthapp.core.ui.kit.animations

import android.animation.TypeEvaluator

class StringEvaluator : TypeEvaluator<String> {
    override fun evaluate(fraction: Float, startValue: String?, endValue: String?): String {
        val coercedFraction = fraction.coerceIn(0f, 1f)

        return endValue!!.substring(0, (endValue.length * coercedFraction).toInt())
    }
}