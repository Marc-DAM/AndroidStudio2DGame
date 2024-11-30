package com.android.example.androidstudio2dgame.gamepanel

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.R
import com.example.androidstudio2dgamedevelopment.GameLoop

class Performance (private val context: Context, private val gameLoop: GameLoop) {

    // Método para dibujar las métricas UPS y FPS
    fun draw(canvas: Canvas) {
        drawUPS(canvas)
        drawFPS(canvas)
    }

    private fun drawUPS(canvas: Canvas) {
        val averageUPS = gameLoop.getAverageUPS().toString()

        val paint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.magenta)
            textSize = 50f
            isAntiAlias = true
        }

        canvas.drawText("UPS: $averageUPS", 100f, 100f, paint)
    }

    private fun drawFPS(canvas: Canvas) {
        val averageFPS = gameLoop.getAverageFPS().toString()

        val paint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.magenta)
            textSize = 50f
            isAntiAlias = true
        }

        canvas.drawText("FPS: $averageFPS", 100f, 200f, paint)
    }
}
