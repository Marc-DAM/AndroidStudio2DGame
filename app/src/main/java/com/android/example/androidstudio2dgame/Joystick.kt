package com.android.example.androidstudio2dgame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Joystick(
    centerPositionX: Int,
    centerPositionY: Int,
    outerCircleRadius: Int,
    innerCircleRadius: Int
) {
    // Propiedades para el joystick
    private var outerCircleRadius: Int = outerCircleRadius
    private var innerCircleRadius: Int = innerCircleRadius

    private var outerCircleCenterPositionX: Int = centerPositionX
    private var outerCircleCenterPositionY: Int = centerPositionY

    private var innerCircleCenterPositionX: Int = centerPositionX
    private var innerCircleCenterPositionY: Int = centerPositionY

    private var innerCirclePaint: Paint = Paint()
    private var outerCirclePaint: Paint = Paint()

    private var joystickCenterToTouchDistance: Double = 0.0
    private var isPressed: Boolean = false
    private var actuatorX: Double = 0.0
    private var actuatorY: Double = 0.0

    init {
        // Inicializar el color y el estilo de los círculos
        outerCirclePaint.apply {
            color = Color.GRAY
            style = Paint.Style.FILL_AND_STROKE
        }

        innerCirclePaint.apply {
            color = Color.LTGRAY
            style = Paint.Style.FILL_AND_STROKE
        }
    }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(
            outerCircleCenterPositionX.toFloat(),
            outerCircleCenterPositionY.toFloat(),
            outerCircleRadius.toFloat(),
            outerCirclePaint
        )
        canvas.drawCircle(
            innerCircleCenterPositionX.toFloat(),
            innerCircleCenterPositionY.toFloat(),
            innerCircleRadius.toFloat(),
            innerCirclePaint
        )
    }

    fun update() {
        updateInnerCirclePosition()
    }

    fun updateInnerCirclePosition(){
        innerCircleCenterPositionX = (outerCircleCenterPositionX + actuatorX * outerCircleRadius).toInt()
        innerCircleCenterPositionY = (outerCircleCenterPositionY + actuatorY * outerCircleRadius).toInt()
    }

    fun isPressed(touchPositionX: Double, touchPositionY: Double): Boolean {
        joystickCenterToTouchDistance = Math.sqrt(
            Math.pow((outerCircleCenterPositionX - touchPositionX), 2.0) +
                    Math.pow((outerCircleCenterPositionY - touchPositionY), 2.0)
        )

        // Verificar si la distancia es menor al radio del círculo exterior
        return joystickCenterToTouchDistance < outerCircleRadius
    }

    fun setIsPressed(isPressed: Boolean) {
        this.isPressed = isPressed
    }

    fun getIsPressed(): Boolean {
        return isPressed
    }

    fun setActuator(touchPositionX: Double, touchPositionY: Double) {
        // Calcular la diferencia entre la posición del toque y el centro del joystick
        val deltaX = touchPositionX - outerCircleCenterPositionX
        val deltaY = touchPositionY - outerCircleCenterPositionY

        // Calcular la distancia entre el toque y el centro del joystick
        val deltaDistance = Math.sqrt(Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2.0))

        // Verificar si el toque está dentro del radio del círculo exterior
        if (deltaDistance < outerCircleRadius) {
            // Si está dentro del radio, el actuator es proporcional a la distancia del centro
            actuatorX = deltaX / outerCircleRadius
            actuatorY = deltaY / outerCircleRadius
        } else {
            // Si el toque está fuera del radio, normalizamos la distancia
            actuatorX = deltaX / deltaDistance
            actuatorY = deltaY / deltaDistance
        }
    }

    fun resetActuator() {
        actuatorX = 0.0
        actuatorY = 0.0
    }

    fun getActuatorX(): Double {
        return actuatorX
    }

    fun getActuatorY(): Double {
        return actuatorY
    }

}
