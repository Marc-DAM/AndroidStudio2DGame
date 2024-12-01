package com.android.example.androidstudio2dgame.map

import SpriteSheet
import android.graphics.Canvas
import android.graphics.Rect
import com.android.example.androidstudio2dgame.graphics.Sprite

class GroundTile(
    spriteSheet: SpriteSheet,
    mapLocationRect: Rect
) : Tile(mapLocationRect) {

    private val sprite: Sprite = spriteSheet.getGroundSprite()

    override fun draw(canvas: Canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top)
    }

}