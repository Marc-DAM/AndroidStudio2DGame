package com.android.example.androidstudio2dgame.graphics

import android.graphics.Canvas
import com.android.example.androidstudio2dgame.GameDisplay
import com.android.example.androidstudio2dgame.gameobject.Enemy
import com.android.example.androidstudio2dgame.gameobject.Player
import com.android.example.androidstudio2dgame.gameobject.PlayerState.State
import com.android.example.androidstudio2dgame.gameobject.Spell

class Animator(private val spriteMap: Map<String, ArrayList<Sprite>>) {

    companion object {
        private const val MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME: Int = 5
    }

    private var updatesBeforeNextMoveFrame: Int = 0
    private var idxMovingFrame: Int = 1
    private var lastDirection: String = "DOWN" // Dirección inicial por defecto

    fun draw(canvas: Canvas, gameDisplay: GameDisplay, player: Player) {
        val direction = determineDirection(player.velocityX, player.velocityY)
        val spriteArray = spriteMap[direction] ?: return

        when (player.retrievePlayerState().getState()) {
            State.NOT_MOVING -> {
                // Usar el sprite "idle" (primer elemento de la lista)
                drawFrame(canvas, gameDisplay, player, spriteArray[0])
            }

            State.STARTED_MOVING -> {
                // Inicializar la animación de movimiento
                updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME
                drawFrame(canvas, gameDisplay, player, spriteArray[idxMovingFrame])
                lastDirection = direction
            }

            State.IS_MOVING -> {
                updatesBeforeNextMoveFrame--
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME
                    toggleIdxMovingFrame()
                }
                drawFrame(canvas, gameDisplay, player, spriteArray[idxMovingFrame])
                lastDirection = direction
            }

            else -> {}
        }
    }

    fun draw(canvas: Canvas, gameDisplay: GameDisplay, spell: Spell) {
        val spellSprites = spriteMap["DEFAULT"] ?: return

        // Alternar entre dos sprites para el hechizo
        updatesBeforeNextMoveFrame--
        if (updatesBeforeNextMoveFrame <= 0) {
            updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME
            toggleIdxSpellFrame() // Alterna entre los sprites
        }

        // Dibuja el hechizo usando el sprite correspondiente
        drawFrame(canvas, gameDisplay, spell, spellSprites[idxMovingFrame])

    }

    fun draw(canvas: Canvas, gameDisplay: GameDisplay, enemy: Enemy) {
        val enemySprites = spriteMap["DEFAULT"] ?: return

        // Alternar entre dos sprites para el hechizo
        updatesBeforeNextMoveFrame--
        if (updatesBeforeNextMoveFrame <= 0) {
            updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME
            toggleIdxSpellFrame() // Alterna entre los sprites
        }

        // Dibuja el hechizo usando el sprite correspondiente
        drawFrame(canvas, gameDisplay, enemy, enemySprites[idxMovingFrame])

    }


    private fun determineDirection(velocityX: Double, velocityY: Double): String {
        return when {
            Math.abs(velocityX) > Math.abs(velocityY) -> { // Prioriza X si es mayor
                if (velocityX > 0) "RIGHT" else "LEFT"
            }

            else -> { // Prioriza Y si es mayor o igual
                if (velocityY > 0) "DOWN" else "UP"
            }
        }
    }

    private fun toggleIdxMovingFrame() {
        if (idxMovingFrame == 1) {
            idxMovingFrame = 2
        } else {
            idxMovingFrame = 1
        }
    }

    private fun toggleIdxSpellFrame() {
        if (idxMovingFrame == 0) {
            idxMovingFrame = 1
        } else {
            idxMovingFrame = 0
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
    fun drawFrame(canvas: Canvas, gameDisplay: GameDisplay, spell: Spell, sprite: Sprite) {
        sprite.draw(
            canvas,
            gameDisplay.gameToDisplayCoordinatesX(spell.retrievePositionX())
                .toInt() - sprite.getWidth() / 2,
            gameDisplay.gameToDisplayCoordinatesY(spell.retrievePositionY())
                .toInt() - sprite.getHeight() / 2
        )
    }
    fun drawFrame(canvas: Canvas, gameDisplay: GameDisplay, enemy: Enemy, sprite: Sprite) {
        sprite.draw(
            canvas,
            gameDisplay.gameToDisplayCoordinatesX(enemy.retrievePositionX())
                .toInt() - sprite.getWidth() / 2,
            gameDisplay.gameToDisplayCoordinatesY(enemy.retrievePositionY())
                .toInt() - sprite.getHeight() / 2
        )
    }


}
