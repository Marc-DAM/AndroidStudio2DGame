import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.`object`.Enemy
import com.android.example.androidstudio2dgame.Joystick
import com.android.example.androidstudio2dgame.`object`.Player
import com.android.example.androidstudio2dgame.R
import com.example.androidstudio2dgamedevelopment.GameLoop

class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val joystick: Joystick
    private val player: Player
    private val enemy: Enemy
    private val gameLoop: GameLoop

    init {
        // Obtener SurfaceHolder y añadir el callback
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)

        // Inicializar GameLoop
        gameLoop = GameLoop(this, surfaceHolder)

        //Inicializar objetos del juego
        joystick = Joystick(275,700,70,40)
        player = Player(getContext(), joystick ,500.0,500.0,30.0)
        enemy = Enemy(getContext(), player, 500.0,200.0,30.0)

        // Hacer que la vista sea focalizable
        isFocusable = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Manejar las acciones del evento táctil
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Verifica si el toque está dentro del área del joystick
                if (joystick.isPressed(event.x.toDouble(), event.y.toDouble())) {
                    joystick.setIsPressed(true) // Marca que el joystick está presionado
                }
                return true

            }
            MotionEvent.ACTION_MOVE -> {
                // Si el joystick está presionado, actualiza su estado (movimiento)
                if (joystick.getIsPressed()) {
                    joystick.setActuator(event.x.toDouble(), event.y.toDouble()) // Actualiza la posición del actuador
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                // Cuando el dedo se levanta, puedes marcar que el joystick ha sido soltado si es necesario
                joystick.setIsPressed(false)
                joystick.resetActuator()
                return true
            }
        }

        return super.onTouchEvent(event)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        gameLoop.startLoop()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Implementa lógica si es necesario
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        // Implementa lógica si es necesario
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        drawUPS(canvas)
        drawFPS(canvas)

        joystick.draw(canvas)
        player.draw(canvas)
        enemy.draw(canvas)
    }

    private fun drawUPS(canvas: Canvas) {
        val averageUPS = gameLoop.getAverageUPS().toString()

        val paint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.magenta)
            textSize = 50f
            isAntiAlias = true
        }

        canvas.drawText("UPS: $averageUPS", 100f, 100f, paint)
    }

    private fun drawFPS(canvas: Canvas) {
        val averageFPS = gameLoop.getAverageFPS().toString()

        val paint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.magenta)
            textSize = 50f
            isAntiAlias = true
        }

        canvas.drawText("FPS: $averageFPS", 100f, 200f, paint)
    }

    fun update(){
        joystick.update()
        player.update()
        enemy.update()
    }
}