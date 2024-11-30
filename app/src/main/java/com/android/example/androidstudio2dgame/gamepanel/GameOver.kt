package com.android.example.androidstudio2dgame.gamepanel

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.R

/**
 *  GameOver is a panel which draws the text Game Over to the screen.
 */
class GameOver (private val context: Context) {

    fun draw(canvas: Canvas) {
        val text = "Game Over"
        val x = 800f
        val y = 200f

        val paint = Paint().apply {
            val color = ContextCompat.getColor(context, R.color.gameOver)
            this.color = color
            textSize = 150f
        }

        canvas.drawText(text, x, y, paint)
    }
}
