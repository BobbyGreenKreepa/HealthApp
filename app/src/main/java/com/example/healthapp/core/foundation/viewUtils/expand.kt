package com.example.healthapp.core.foundation.viewUtils

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation

fun View.expandOrCollapse(expand: Boolean) {
    if (expand) expand(this) else collapse(this)
}

fun expand(view: View) {
    val animation = expandAction(view)
    view.startAnimation(animation)
}

private fun expandAction(view: View): Animation {
    view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val currentHeight: Int = view.measuredHeight
    view.layoutParams.height = 0
    view.visibility = (View.VISIBLE)

    val animation: Animation = object : Animation() {

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            view.layoutParams.height = if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (currentHeight * interpolatedTime).toInt()
            view.requestLayout()
        }
    }

    animation.duration = (currentHeight / view.context.resources.displayMetrics.density).toLong()
    view.startAnimation(animation)

    return animation
}

fun collapse(view: View) {
    val currentHeight: Int = view.measuredHeight

    val animation: Animation = object : Animation() {

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            if (interpolatedTime == 1f) {
                view.visibility = View.GONE
            } else {
                view.layoutParams.height = currentHeight - (currentHeight * interpolatedTime).toInt()
                view.requestLayout()
            }
        }
    }

    animation.duration = (currentHeight / view.context.resources.displayMetrics.density).toLong()
    view.startAnimation(animation)
}