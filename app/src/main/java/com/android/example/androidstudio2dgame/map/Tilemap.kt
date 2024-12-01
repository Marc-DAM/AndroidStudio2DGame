package com.android.example.androidstudio2dgame.map

import SpriteSheet
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log
import com.android.example.androidstudio2dgame.GameDisplay
import com.android.example.androidstudio2dgame.map.MapLayout.Companion.NUMBER_OF_COLUMN_TILES
import com.android.example.androidstudio2dgame.map.MapLayout.Companion.NUMBER_OF_ROW_TILES
import com.android.example.androidstudio2dgame.map.MapLayout.Companion.TILE_HEIGHT_PIXELS
import com.android.example.androidstudio2dgame.map.MapLayout.Companion.TILE_WIDTH_PIXELS


class Tilemap(private val spriteSheet: SpriteSheet) {
    private val mapLayout: MapLayout = MapLayout()
    private lateinit var tilemap: Array<Array<Tile?>>
    private lateinit var mapBitmap: Bitmap

    init {
        initializeTilemap()
    }

    private fun initializeTilemap() {
        val layout = mapLayout.getLayout()
        tilemap = Array(NUMBER_OF_ROW_TILES) { arrayOfNulls<Tile>(NUMBER_OF_COLUMN_TILES) }
        for (iRow in 0 until NUMBER_OF_ROW_TILES) {
            for (iCol in 0 until NUMBER_OF_COLUMN_TILES) {
                tilemap[iRow][iCol] = Tile.getTile(
                    layout[iRow][iCol],
                    spriteSheet,
                    getRectByIndex(iRow, iCol)
                )
            }
        }


        val config = Bitmap.Config.ARGB_8888
        mapBitmap = Bitmap.createBitmap(
            NUMBER_OF_COLUMN_TILES * TILE_WIDTH_PIXELS,
            NUMBER_OF_ROW_TILES * TILE_HEIGHT_PIXELS,
            config
        )

        Log.d("Tilemap.kt", "mapBitmap width: ${mapBitmap.width}, height: ${mapBitmap.height}")

        val mapCanvas = Canvas(mapBitmap)

        Log.d("Tilemap.kt", "mapCanvas width: ${mapCanvas.width}, height: ${mapCanvas.height}")

        for (iRow in 0 until NUMBER_OF_ROW_TILES) {
            for (iCol in 0 until NUMBER_OF_COLUMN_TILES) {
                tilemap[iRow][iCol]?.draw(mapCanvas)
            }
        }

    System.out.println()


    }

    private fun getRectByIndex(idxRow: Int, idxCol: Int): Rect {
        return Rect(
            idxCol * TILE_WIDTH_PIXELS,
            idxRow * TILE_HEIGHT_PIXELS,
            (idxCol + 1) * TILE_WIDTH_PIXELS,
            (idxRow + 1) * TILE_HEIGHT_PIXELS
        )
    }

    fun draw(canvas: Canvas, gameDisplay: GameDisplay) {
        canvas.drawBitmap(
            mapBitmap,
            gameDisplay.getGameRect(),
            gameDisplay.DISPLAY_RECT,
            null
        )

    }
}