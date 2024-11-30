package com.android.example.androidstudio2dgame

import com.android.example.androidstudio2dgame.`object`.GameObject

public class GameDisplay(widthPixels: Int, heightPixels: Int, private val centerObject: GameObject) {

    private var gameToDisplayCoordinateOffsetX: Double = 0.0
    private var gameToDisplayCoordinateOffsetY: Double = 0.0
    private var displayCenterX: Double = widthPixels / 2.0
    private var displayCenterY: Double = heightPixels / 2.0
    private var gameCenterX: Double = 0.0
    private var gameCenterY: Double = 0.0

    fun update() {
        gameCenterX = centerObject.retrievePositionX()
        gameCenterY = centerObject.retrievePositionY()
        gameToDisplayCoordinateOffsetX = displayCenterX - gameCenterX
        gameToDisplayCoordinateOffsetY = displayCenterY - gameCenterY
    }

    fun gameToDisplayCoordinatesX(x: Double): Double {
        return x + gameToDisplayCoordinateOffsetX
    }

    fun gameToDisplayCoordinatesY(y: Double): Double {
        return y + gameToDisplayCoordinateOffsetY
    }
}
