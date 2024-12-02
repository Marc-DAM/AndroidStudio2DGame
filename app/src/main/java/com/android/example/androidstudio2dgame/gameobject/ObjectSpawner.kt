package com.android.example.androidstudio2dgame.gameobject

import android.content.Context
import com.android.example.androidstudio2dgame.map.MapLayout.Companion.NUMBER_OF_COLUMN_TILES
import com.android.example.androidstudio2dgame.map.MapLayout.Companion.NUMBER_OF_ROW_TILES
import com.android.example.androidstudio2dgame.map.MapLayout.Companion.TILE_HEIGHT_PIXELS
import com.android.example.androidstudio2dgame.map.MapLayout.Companion.TILE_WIDTH_PIXELS
import com.android.example.androidstudio2dgame.map.Tilemap
import kotlin.random.Random

class ObjectSpawner(private val context: Context) {

    companion object {
        private const val LIFE_BOOST_CHANCE = 0.2

        fun spawnRandomObject(context: Context, tilemap: Tilemap): Circle {
            // Obtener las dimensiones del mapa desde el Tilemap
            val mapWidth = NUMBER_OF_COLUMN_TILES * TILE_WIDTH_PIXELS
            val mapHeight = NUMBER_OF_ROW_TILES * TILE_HEIGHT_PIXELS

            // Generar posición aleatoria dentro del mapa, en función de las dimensiones del mapa
            val positionX = Random.nextDouble(0.0, mapWidth.toDouble())
            val positionY = Random.nextDouble(0.0, mapHeight.toDouble())

            // Crear el objeto dependiendo de la probabilidad
            return if (Random.nextDouble() < LIFE_BOOST_CHANCE) {
                LifeBoost(context, positionX, positionY)
            } else {
                ScoreBoost(context, positionX, positionY)
            }
        }
    }
}