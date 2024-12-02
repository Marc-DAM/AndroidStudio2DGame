package com.android.example.androidstudio2dgame.gameobject

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.GameDisplay
import com.android.example.androidstudio2dgame.R

class ScoreBoost(
    context: Context,
    positionX: Double,
    positionY: Double,
    radius: Double = 20.0
) : Circle(
    context,
    ContextCompat.getColor(context, R.color.scoreBoost), // Define un color para ScoreBoost en colors.xml
    positionX,
    positionY,
    radius
) {
    override fun update() {
        // Los objetos de tipo ScoreBoost no necesitan moverse.
    }

    override fun draw(canvas: Canvas, gameDisplay: GameDisplay) {
        super.draw(canvas, gameDisplay)
    }
}