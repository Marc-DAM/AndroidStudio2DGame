package com.android.example.androidstudio2dgame.gameobject

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.GameDisplay
import com.android.example.androidstudio2dgame.R

class LifeBoost(
    context: Context,
    positionX: Double,
    positionY: Double,
    radius: Double = 20.0
) : Circle(
    context,
    ContextCompat.getColor(context, R.color.lifeBoost), // Define un color para LifeBoost en colors.xml
    positionX,
    positionY,
    radius
) {
    override fun update() {
        // Los objetos de tipo LifeBoost no necesitan moverse.
    }

    override fun draw(canvas: Canvas, gameDisplay: GameDisplay) {
        super.draw(canvas, gameDisplay)
    }
}