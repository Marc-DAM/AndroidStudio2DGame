package com.android.example.androidstudio2dgame.map

import SpriteSheet
import android.graphics.Canvas
import android.graphics.Rect

abstract class Tile(protected val mapLocationRect: Rect) {

    enum class TileType {
        WATER_TILE,
        LAVA_TILE,
        GROUND_TILE,
        GRASS_TILE,
        TREE_TILE
    }

    companion object {
        fun getTile(idxTileType: Int, spriteSheet: SpriteSheet, mapLocationRect: Rect): Tile {
            val tileType = Tile.TileType.values()[idxTileType]
            return when (tileType) {
                Tile.TileType.GROUND_TILE -> GroundTile(spriteSheet, mapLocationRect)
                Tile.TileType.LAVA_TILE -> LavaTile(spriteSheet, mapLocationRect)
                Tile.TileType.WATER_TILE -> WaterTile(spriteSheet, mapLocationRect)
                Tile.TileType.GRASS_TILE -> GrassTile(spriteSheet, mapLocationRect)
                Tile.TileType.TREE_TILE -> TreeTile(spriteSheet, mapLocationRect)
            }
        }
    }

    abstract fun draw(canvas: Canvas)

}
