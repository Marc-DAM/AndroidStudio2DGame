package com.android.example.androidstudio2dgame.gamepanel

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.R

/**
 *  Points is a panel that tracks and displays the player's score.
 */
class Points(private val context: Context) {

    private var score: Int = 0

    // Increment the score
    fun addPoints(points: Int) {
        score += points
    }

    // Draw the score on the screen
    fun draw(canvas: Canvas) {
        val x = 50f
        val y = 100f

        val paint = Paint().apply {
            val color = ContextCompat.getColor(context, R.color.white) // Usa un color adecuado de tu archivo colors.xml
            this.color = color
            textSize = 80f
            isAntiAlias = true
        }

        canvas.drawText("Punts: $score", x, y, paint)
    }
}