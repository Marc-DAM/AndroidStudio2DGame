package com.android.example.androidstudio2dgame.`object`

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint

/**
 * Circle is an abstract class which implements a draw method from Gameobject for drawing the object
 * as a circle.
 */
abstract class Circle(
    context: Context,
    color: Int,
    positionX: Double,
    positionY: Double,
    protected val radius: Double
) : GameObject(positionX, positionY) {

    // Set colors of circle
    protected val paint: Paint = Paint().apply {
        this.color = color
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(positionX.toFloat(), positionY.toFloat(), radius.toFloat(), paint)
    }
}