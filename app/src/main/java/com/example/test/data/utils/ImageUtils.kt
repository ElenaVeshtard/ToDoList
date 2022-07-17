package com.example.test.data.utils

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView

class ImageUtils {
    companion object {
        fun setRoundRect(imageView: ImageView) {
            imageView.clipToOutline = true
            imageView.outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    val radius = view.height / 2
                    val centre = view.width / 2
                    outline.setRoundRect(
                        centre - radius, 0,
                        centre + radius, view.height,
                        radius.toFloat()
                    )
                }
            }
        }
    }
}