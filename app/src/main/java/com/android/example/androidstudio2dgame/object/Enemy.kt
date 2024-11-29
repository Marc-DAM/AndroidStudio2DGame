package com.android.example.androidstudio2dgame.`object`

import android.content.Context
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.R
import com.example.androidstudio2dgamedevelopment.GameLoop

/**
 * Enemy is a character which always moves in the direction of the player.
 * The Enemy class is an extension of a Circle, which is an extension of a GameObject
 */
class Enemy(
    context: Context,
    private val player: Player,
    positionX: Double,
    positionY: Double,
    radius: Double
) : Circle(
    context,
    ContextCompat.getColor(context, R.color.enemy), // Color del enemigo
    positionX,
    positionY,
    radius
) {
    companion object {
        private const val SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND *0.6
        private const val MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS
    }

    override fun update() {
        // Calcular la distancia del enemigo al jugador (en x e y)
        val distanceToPlayerX = player.retrievePositionX() - positionX  // Acceso directo a positionX
        val distanceToPlayerY = player.retrievePositionY() - positionY  // Acceso directo a positionY

        // Calcular la distancia (absoluta) entre el enemigo (this) y el jugador
        val distanceToPlayer = getDistanceBetweenObjects(this, player)

        // Calcular dirección desde el enemigo hacia el jugador
        val directionX = distanceToPlayerX / distanceToPlayer
        val directionY = distanceToPlayerY / distanceToPlayer

        // Establecer la velocidad en la dirección del jugador
        if (distanceToPlayer > 0) { // Evitar la división por cero
            velocityX = directionX * MAX_SPEED
            velocityY = directionY * MAX_SPEED
        } else {
            velocityX = 0.0
            velocityY = 0.0
        }

        // Actualizar la posición del enemigo
        positionX += velocityX
        positionY += velocityY
    }
}
