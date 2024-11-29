import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.R
import com.example.androidstudio2dgamedevelopment.GameLoop

class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val gameLoop: GameLoop

    init {
        // Obtener SurfaceHolder y añadir el callback
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)

        // Inicializar GameLoop
        gameLoop = GameLoop(this, surfaceHolder)

        // Hacer que la vista sea focalizable
        isFocusable = true
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

    fun update(){}
}