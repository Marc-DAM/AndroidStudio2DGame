package com.android.example.androidstudio2dgame.map

import SpriteSheet
import android.graphics.Canvas
import android.graphics.Rect
import com.android.example.androidstudio2dgame.graphics.Sprite

class LavaTile(
    spriteSheet: SpriteSheet,
    mapLocationRect: Rect
) : Tile(mapLocationRect) {

    private val sprite: Sprite = spriteSheet.getLavaSprite()

    override fun draw(canvas: Canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top)
    }

}