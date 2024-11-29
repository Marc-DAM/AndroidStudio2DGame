package com.example.androidstudio2dgamedevelopment

import Game
import android.graphics.Canvas
import android.view.SurfaceHolder


class GameLoop(private val game: Game, private val surfaceHolder: SurfaceHolder) : Thread() {

     companion object {
        const val MAX_UPS = 30.0
        private const val UPS_PERIOD = 1000.0 / MAX_UPS
    }

    private var averageFPS: Double = 0.0
    private var averageUPS: Double = 0.0
    private var isRunning = false

    // Este método devuelve el promedio de UPS (Actualizaciones por segundo)
    fun getAverageUPS(): Double {
        return averageUPS
    }

    // Este método devuelve el promedio de FPS (Cuadros por segundo)
    fun getAverageFPS(): Double {
        return averageFPS
    }

    // Método para iniciar el bucle del juego
    fun startLoop() {
        isRunning = true
        start()
    }

    override fun run() {
        super.run()


        // Declare time and cycle count variables
        var updateCount = 0
        var frameCount = 0
        var startTime: Long
        var elapsedTime: Long
        var sleepTime: Long


        // Bucle principal del juego
        var canvas: Canvas? = null
        startTime = System.currentTimeMillis();
        while (isRunning) {
            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    // Actualizar el juego
                    game.update()
                    updateCount++
                    // Dibujar el juego
                    if (canvas != null) {
                        game.draw(canvas)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                // Liberar el canvas después de dibujar
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                        frameCount++
                    } catch (e: Exception) {
                        TODO("Not yet implemented")
                    }
                }
            }


            // Lógica para controlar el UPS y FPS (esto se puede personalizar)
            // Por ejemplo, pausar el bucle para no exceder el UPS objetivo
            try {
                Thread.sleep(1000 / 60) // Limitar a 60 FPS (puedes ajustar según lo necesites)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }


            // Pausar el game loop para no exceder el target UPS
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = ((updateCount * UPS_PERIOD - elapsedTime).toLong());
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            // Si el tiempo de espera es negativo, actualizar el juego
            while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
                game.update()  // Actualizar el juego
                updateCount++  // Contar la cantidad de actualizaciones realizadas
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = ((updateCount * UPS_PERIOD - elapsedTime).toLong());
            }

            // Calcular el promedio de UPS y FPS
            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (1E-3 * elapsedTime);  // Updates per second (UPS)
                averageFPS = frameCount / (1E-3 * elapsedTime);  // Frames per second (FPS)

                // Reset counters for next calculation period
                updateCount = 0;
                frameCount = 0;

                // Reset start time for the next 1-second interval
                startTime = System.currentTimeMillis();
            }

        }
    }

    // Método para detener el bucle
    fun stopLoop() {
        isRunning = false
    }
}