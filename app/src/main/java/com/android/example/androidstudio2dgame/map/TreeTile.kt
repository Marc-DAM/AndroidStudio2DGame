package com.android.example.androidstudio2dgame.map

import SpriteSheet
import android.graphics.Canvas
import android.graphics.Rect
import com.android.example.androidstudio2dgame.graphics.Sprite

class TreeTile(
    spriteSheet: SpriteSheet,
    mapLocationRect: Rect
) : Tile(mapLocationRect) {

    private val grassSprite: Sprite = spriteSheet.getGrassSprite()
    private val treeSprite: Sprite = spriteSheet.getTreeSprite()


    override fun draw(canvas: Canvas) {
        grassSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top)
        treeSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top)
    }

}