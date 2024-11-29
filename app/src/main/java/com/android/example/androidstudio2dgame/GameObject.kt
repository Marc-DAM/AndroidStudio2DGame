package com.android.example.androidstudio2dgame

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

}

