package com.android.example.androidstudio2dgame.`object`

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.Joystick
import com.android.example.androidstudio2dgame.R
import com.android.example.androidstudio2dgame.Utils
import com.example.androidstudio2dgamedevelopment.GameLoop

/**
 *  Player is the main character of the game, which the user can control with a touch joystick.
 *  The player class is an extension of a Circle, which is an extension of a Gameobject
 */

class Player(
    context: Context,
    private val joystick: Joystick,
    positionX: Double,
    positionY: Double,
    radius: Double
) : Circle(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius) {

    companion object {
        const val SPEED_PIXELS_PER_SECOND = 400.0
        private const val MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(positionX.toFloat(), positionY.toFloat(), radius.toFloat(), paint)
    }

    override fun update() {
        // Update velocity based on actuator of joystick
        velocityX = joystick.getActuatorX() * MAX_SPEED
        velocityY = joystick.getActuatorY() * MAX_SPEED

        //Update position
        positionX += velocityX
        positionY += velocityY

        // Update direction
        if(velocityX != 0.0 || velocityY != 0.0){
            // Normalize velocity to get direction (unit vector of velocity)
            val distance: Double = Utils.getDistanceBetweenPoints(0.0, 0.0, velocityX, velocityY)
            directionX = velocityX / distance
            directionY = velocityY / distance        }

    }
}