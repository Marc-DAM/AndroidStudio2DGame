package com.android.example.androidstudio2dgame

import android.graphics.Rect
import com.android.example.androidstudio2dgame.gameobject.GameObject

public class GameDisplay(widthPixels: Int, heightPixels: Int, private val centerObject: GameObject) {

    val DISPLAY_RECT: Rect = Rect(0, 0, widthPixels, heightPixels)
    private val heightPixels: Int = heightPixels
    private val widthPixels: Int = widthPixels
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

    fun getGameRect(): Rect {
        return Rect(
            (gameCenterX -  widthPixels / 2).toInt(),
            (gameCenterY - heightPixels / 2).toInt(),
            (gameCenterX + widthPixels / 2).toInt(),
            (gameCenterY + heightPixels / 2).toInt()
        )
    }
}
