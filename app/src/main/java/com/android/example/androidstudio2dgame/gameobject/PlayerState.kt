package com.android.example.androidstudio2dgame.gameobject

class PlayerState(private val player: Player) {

    enum class State {
        NOT_MOVING,
        STARTED_MOVING,
        IS_MOVING
    }

    enum class Direction {
        DOWN,   // Mirando hacia abajo (por defecto)
        UP,     // Mirando hacia arriba
        LEFT,   // Mirando hacia la izquierda
        RIGHT   // Mirando hacia la derecha
    }

    private var state: State = State.NOT_MOVING
    private var direction: Direction = Direction.DOWN // Dirección inicial predeterminada

    fun getState(): State {
        return state
    }

    fun getDirection(): Direction {
        return direction
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
        // Actualizar dirección según la velocidad
        if (player.velocityY > 0) {
            direction = Direction.DOWN
        } else if (player.velocityY < 0) {
            direction = Direction.UP
        } else if (player.velocityX > 0) {
            direction = Direction.RIGHT
        } else if (player.velocityX < 0) {
            direction = Direction.LEFT
        }
    }
}