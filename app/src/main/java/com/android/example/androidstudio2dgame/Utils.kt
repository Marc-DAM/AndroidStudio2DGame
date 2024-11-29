package com.android.example.androidstudio2dgame

object Utils {

    /**
     * getDistanceBetweenPoints returns the distance between 2d points pl and p2
     */
    @JvmStatic
    fun getDistanceBetweenPoints(p1x: Double, p1y: Double, p2x: Double, p2y: Double): Double {
        return Math.sqrt(Math.pow(p1x - p2x, 2.0) + Math.pow(p1y - p2y, 2.0))
    }
}