package com.android.example.androidstudio2dgame.gameobject

import android.content.Context
import kotlin.random.Random

class ObjectSpawner(private val context: Context) {

    companion object {
        private const val LIFE_BOOST_CHANCE = 0.5

        fun spawnRandomObject(context: Context): Circle {
            val positionX = Random.nextDouble(0.0, 1000.0)
            val positionY = Random.nextDouble(0.0, 1000.0)

            return if (Random.nextDouble() < LIFE_BOOST_CHANCE) {
                LifeBoost(context, positionX, positionY)
            } else {
                ScoreBoost(context, positionX, positionY)
            }
        }
    }
}