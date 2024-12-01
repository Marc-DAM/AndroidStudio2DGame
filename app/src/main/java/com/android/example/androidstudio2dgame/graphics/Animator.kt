package com.android.example.androidstudio2dgame.graphics

import android.graphics.Canvas
import com.android.example.androidstudio2dgame.GameDisplay
import com.android.example.androidstudio2dgame.gameobject.Player
import com.android.example.androidstudio2dgame.gameobject.PlayerState.State

class Animator(private val playerSpriteArray: ArrayList<Sprite>) {

    companion object {
        private const val MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME: Int = 5
    }

    private var updatesBeforeNextMoveFrame: Int = 0
    private var idxMovingFrame: Int = 1
    private val idxNotMovingFrame: Int = 0

    fun getSprite(index: Int): Sprite {
        return playerSpriteArray[index]
    }

    fun draw(canvas: Canvas, gameDisplay: GameDisplay, player: Player) {
        when (player.retrievePlayerState().getState()) {
            State.NOT_MOVING -> {
                drawFrame(canvas, gameDisplay, player, getSprite(idxNotMovingFrame))
            }

            State.STARTED_MOVING -> {
                updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME
                drawFrame(canvas, gameDisplay, player, getSprite(idxMovingFrame))
            }

            State.IS_MOVING -> {
                updatesBeforeNextMoveFrame--
                if(updatesBeforeNextMoveFrame == 0){
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME
                    toggleIdxMovingFrame()
                }
                drawFrame(canvas, gameDisplay, player, getSprite(idxMovingFrame))
            }

            else -> {}
        }
    }

    private fun toggleIdxMovingFrame() {
        if(idxMovingFrame == 1) {
            idxMovingFrame = 2
        } else {
            idxMovingFrame = 1
        }
    }

    fun drawFrame(canvas: Canvas, gameDisplay: GameDisplay, player: Player, sprite: Sprite) {
        sprite.draw(
            canvas,
            gameDisplay.gameToDisplayCoordinatesX(player.retrievePositionX())
                .toInt() - sprite.getWidth() / 2,
            gameDisplay.gameToDisplayCoordinatesY(player.retrievePositionY())
                .toInt() - sprite.getHeight() / 2
        )
    }

}
