package com.android.example.androidstudio2dgame.graphics

import SpriteSheet
import android.graphics.Canvas
import android.graphics.Rect

class Sprite (
    private val spriteSheet: SpriteSheet,
    private val rect: Rect
) {

    fun draw(canvas: Canvas, x: Int, y: Int) {
        canvas.drawBitmap(
            spriteSheet.getBitmap(),
            rect,
            Rect(x, y,x+getWidth(),y+getHeight()),
            null
        )
    }

    fun getWidth() : Int {
        return rect.width()
    }

    fun getHeight() : Int {
        return rect.height()
    }

}
