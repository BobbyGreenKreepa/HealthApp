package com.example.healthapp.core.foundation.map

import android.content.res.Resources
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.yandex.runtime.image.ImageProvider

fun fromDrawable(drawableId: Int, resources: Resources) : ImageProvider {
    return ImageProvider.fromBitmap(ResourcesCompat.getDrawable(resources, drawableId, null)?.toBitmap())
}