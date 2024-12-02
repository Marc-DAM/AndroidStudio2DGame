package com.android.example.androidstudio2dgame.gameobject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.GameDisplay
import com.android.example.androidstudio2dgame.gamepanel.Joystick
import com.android.example.androidstudio2dgame.R
import com.android.example.androidstudio2dgame.Utils
import com.android.example.androidstudio2dgame.gamepanel.HealthBar
import com.android.example.androidstudio2dgame.graphics.Animator
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
    radius: Double,
    private val animator: Animator
) : Circle(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius) {

    companion object {
        const val SPEED_PIXELS_PER_SECOND = 400.0
        private const val MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS
        const val MAX_HEALTH_POINTS = 10.0
    }

    var healthPoints: Double = MAX_HEALTH_POINTS
    private val healthBar: HealthBar = HealthBar(context, this)

    // Declarar las propiedades velocityX y velocityY
    override var velocityX: Double = 0.0
    override var velocityY: Double = 0.0

    // Estado del jugador
    private val playerState: PlayerState = PlayerState(this)

    //==================================
    // No hauria d'estar el drawCircle en un altre cantó?
    // El mateix per els arguments de player: haurien d'estar heredats?
    //==================================
    override fun draw(canvas: Canvas, gameDisplay: GameDisplay) {
        animator.draw(canvas, gameDisplay, this)
        healthBar.draw(canvas, gameDisplay)
    }

    override fun update() {
        // Update velocity based on actuator of joystick
        velocityX = joystick.getActuatorX() * MAX_SPEED
        velocityY = joystick.getActuatorY() * MAX_SPEED

        //Update position
        positionX += velocityX
        positionY += velocityY

        var mapRect = Rect(0, 0, 3840, 3840)

        positionX = positionX.coerceIn(
            mapRect.left.toDouble() + radius,
            mapRect.right.toDouble() - radius
        )
        positionY = positionY.coerceIn(
            mapRect.top.toDouble() + radius,
            mapRect.bottom.toDouble() - radius
        )

        // Update direction
        if (velocityX != 0.0 || velocityY != 0.0) {
            // Normalize velocity to get direction (unit vector of velocity)
            val distance: Double = Utils.getDistanceBetweenPoints(0.0, 0.0, velocityX, velocityY)
            directionX = velocityX / distance
            directionY = velocityY / distance
        }

        playerState.update()


    }

    fun retrieveHealthPoints(): Double {
        return healthPoints
    }

    fun settingHealthPoints(healthPoints: Double) {
        if (healthPoints >= 0) {
            this.healthPoints = healthPoints
        }
    }

    fun retrievePlayerState(): PlayerState {
        return playerState
    }

    fun increaseHealth(amount: Int) {
        healthPoints = (healthPoints + amount).coerceAtMost(MAX_HEALTH_POINTS)
    }


}