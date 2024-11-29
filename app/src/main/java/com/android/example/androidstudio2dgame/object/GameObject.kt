package com.android.example.androidstudio2dgame.`object`

import android.graphics.Canvas

/**
 * GameObject is an abstract class which is the foundation of all world objects in the game.
 */
abstract class GameObject(
    protected var positionX: Double,
    protected var positionY: Double,
) {
    protected var velocityX: Double = 0.0
    protected var velocityY: Double = 0.0

    abstract fun draw(canvas: Canvas)
    abstract fun update()

    fun retrievePositionX(): Double {
        return positionX
    }
    fun retrievePositionY(): Double {
        return positionY
    }

    companion object {
        fun getDistanceBetweenObjects(obj1: GameObject, obj2: GameObject): Double {
            return Math.sqrt(
                Math.pow((obj2.retrievePositionX() - obj1.retrievePositionX()), 2.0) +
                        Math.pow((obj2.retrievePositionY() - obj1.retrievePositionY()), 2.0)
            )
        }
    }

}

