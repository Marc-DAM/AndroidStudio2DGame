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
    //===========================================================
    // MIRAR SI ES POT IMPLEMENTAR EL MÉTODE OVERLAPS
    //===========================================================
    /**
     * isColliding checks if two Circle objects are colliding, based on their positions and radio
     * @param obj1 The first circle
     * @param obj2 The second circle
     * @return true if the circles are colliding, false otherwise
     */
    companion object {
        fun isColliding(obj1: Circle, obj2: Circle): Boolean {
            val distance = getDistanceBetweenObjects(obj1, obj2)
            val distanceToCollision = obj1.retrieveRadius() + obj2.retrieveRadius()
            return distance < distanceToCollision
        }
    }

    private fun retrieveRadius(): Double {
        return radius
    }
}