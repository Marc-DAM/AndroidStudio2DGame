package com.android.example.androidstudio2dgame.gamepanel

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.GameDisplay
import com.android.example.androidstudio2dgame.R
import com.android.example.androidstudio2dgame.gameobject.Player

/**
 *  HealthBar displays the players health to the screen
 */
class HealthBar(private val context: Context, private val player: Player) {

    private val width = 100
    private val height = 20
    private val margin = 2

    private val borderPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.healthBarBorder)
        style = Paint.Style.STROKE
    }
    private val healthPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.healthBarHealth)
        style = Paint.Style.FILL
    }

    fun draw(canvas: Canvas, gameDisplay: GameDisplay) {
        val x = player.retrievePositionX()
        val y = player.retrievePositionY()
        val distanceToPlayer = 30
        val healthPointsPercentage = player.retrieveHealthPoints() / Player.MAX_HEALTH_POINTS

        // Draw border
        val borderLeft = (x - width / 2).toFloat()
        val borderRight = (x + width / 2).toFloat()
        val borderBottom = (y - distanceToPlayer).toFloat()
        val borderTop = borderBottom - height
        canvas.drawRect(
            gameDisplay.gameToDisplayCoordinatesX(borderLeft.toDouble()).toFloat(),
            gameDisplay.gameToDisplayCoordinatesY(borderTop.toDouble()).toFloat(),
            gameDisplay.gameToDisplayCoordinatesX(borderRight.toDouble()).toFloat(),
            gameDisplay.gameToDisplayCoordinatesY(borderBottom.toDouble()).toFloat(),
            borderPaint
        )

        // Draw health
        val healthWidth = width - 2 * margin
        val healthHeight = height - 2 * margin
        val healthLeft = borderLeft + margin
        val healthRight = (healthLeft + healthWidth * healthPointsPercentage).toFloat()
        val healthBottom = borderBottom - margin
        val healthTop = healthBottom - healthHeight
        canvas.drawRect(
            gameDisplay.gameToDisplayCoordinatesX(healthLeft.toDouble()).toFloat(),
            gameDisplay.gameToDisplayCoordinatesY(healthTop.toDouble()).toFloat(),
            gameDisplay.gameToDisplayCoordinatesX(healthRight.toDouble()).toFloat(),
            gameDisplay.gameToDisplayCoordinatesY(healthBottom.toDouble()).toFloat(),
            healthPaint
        )

    }
}