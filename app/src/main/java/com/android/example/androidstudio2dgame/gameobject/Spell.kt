package com.android.example.androidstudio2dgame.gameobject

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.GameDisplay
import com.android.example.androidstudio2dgame.R
import com.android.example.androidstudio2dgame.graphics.Animator
import com.example.androidstudio2dgamedevelopment.GameLoop

class Spell(
    context: Context,
    private val spellcaster: Player,
    private val animator: Animator
) : Circle(
    context,
    ContextCompat.getColor(context, R.color.spell),
    spellcaster.retrievePositionX(),
    spellcaster.retrievePositionY(),
    15.0 // radio
) {

    companion object {
        const val SPEED_PIXELS_PER_SECOND = 800.0
        val MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS
    }

    init {
        velocityX = spellcaster.retrieveDirectionX() * MAX_SPEED
        velocityY = spellcaster.retrieveDirectionY() * MAX_SPEED
    }

    override fun update() {
        positionX += velocityX
        positionY += velocityY

    }

    override fun draw(canvas: Canvas, gameDisplay: GameDisplay) {
        animator.draw(canvas, gameDisplay, this)
    }
}