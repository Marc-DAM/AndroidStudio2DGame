package com.android.example.androidstudio2dgame.`object`

import com.android.example.androidstudio2dgame.`object`.Player

class PlayerState(private val player: Player) {

    enum class State {
        NOT_MOVING,
        STARTED_MOVING,
        IS_MOVING
    }

    private var state: State = State.NOT_MOVING

    fun getState(): State {
        return state
    }

    fun update() {
        when (state) {
            State.NOT_MOVING -> {
                if (player.velocityX != 0.0 || player.velocityY != 0.0){
                    state = State.STARTED_MOVING
                }
            }
            State.STARTED_MOVING -> {
                if (player.velocityX != 0.0 && player.velocityY != 0.0){
                    state = State.IS_MOVING
                }
            }
            State.IS_MOVING -> {
                if (player.velocityX == 0.0 && player.velocityY == 0.0){
                    state = State.NOT_MOVING
                }
            }
            else -> {}

        }
    }
}