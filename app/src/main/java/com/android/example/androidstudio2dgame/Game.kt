import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.Player
import com.android.example.androidstudio2dgame.R
import com.example.androidstudio2dgamedevelopment.GameLoop

class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val player: Player
    private val gameLoop: GameLoop

    init {
        // Obtener SurfaceHolder y añadir el callback
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)

        // Inicializar GameLoop
        gameLoop = GameLoop(this, surfaceHolder)

        //Inicializar jugador
        player = Player(getContext(), 500.0,500.0,30.0)

        // Hacer que la vista sea focalizable
        isFocusable = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Manejar las acciones del evento táctil
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Cambiar la posición del jugador cuando se toca la pantalla
                player.setPosition(event.x.toDouble(), event.y.toDouble())
                return true  // Indicar que el evento se ha manejado correctamente
            }
            MotionEvent.ACTION_MOVE -> {
                // Cambiar la posición del jugador cuando se mueve la pantalla
                player.setPosition(event.x.toDouble(), event.y.toDouble())
                return true  // Indicar que el evento se ha manejado correctamente
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

        player.draw(canvas)
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
        player.update()
    }
}