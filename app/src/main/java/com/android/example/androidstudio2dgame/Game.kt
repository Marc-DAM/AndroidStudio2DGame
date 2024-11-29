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
import com.android.example.androidstudio2dgame.`object`.Spell
import com.android.example.androidstudio2dgame.`object`.Circle
import com.example.androidstudio2dgamedevelopment.GameLoop

class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val joystick: Joystick
    private val player: Player
    private val gameLoop: GameLoop
    private val enemyList: MutableList<Enemy> = mutableListOf()
    private val spellList: MutableList<Spell> = mutableListOf()
    private var joystickPointerId: Int = 0


    init {
        // Obtener SurfaceHolder y añadir el callback
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)

        // Inicializar GameLoop
        gameLoop = GameLoop(this, surfaceHolder)

        //Inicializar objetos del juego
        joystick = Joystick(275, 700, 70, 40)
        player = Player(getContext(), joystick, 500.0, 500.0, 30.0)

        // Hacer que la vista sea focalizable
        isFocusable = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Manejar las acciones del evento táctil
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN  -> {
                if (joystick.getIsPressed()) {
                    // Joystick already pressed before this event -> cast spell
                    spellList.add(Spell(context, player))
                } else if (joystick.isPressed(event.x.toDouble(), event.y.toDouble())) {
                    // Joystick is pressed in this event -> setIsPressed(true) and store ID
                    joystickPointerId = event.getPointerId(event.actionIndex)
                    joystick.setIsPressed(true) // Marca que el joystick está presionado
                } else {
                    // Joystick was not previously, and is not pressed in this event -> cast spell
                    spellList.add(Spell(context, player))
                }
                return true

            }

            MotionEvent.ACTION_MOVE -> {
                // Joystick was pressed previously and is now moved
                if (joystick.getIsPressed()) {
                    joystick.setActuator(
                        event.x.toDouble(),
                        event.y.toDouble()
                    ) // Actualiza la posición del actuador
                }
                return true
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                if(joystickPointerId == event.getPointerId(event.actionIndex)){
                    // Joystick was let go of -> setIsPressed(false) and resetActuator
                    joystick.setIsPressed(false)
                    joystick.resetActuator()
                }
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
        for (enemy in enemyList) {
            enemy.draw(canvas)
        }
        for (spell in spellList) {
            spell.draw(canvas)
        }
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

    fun update() {
        joystick.update()
        player.update()

        // Spawn enemy if is time to spawn new enemies
        if (Enemy.readyToSpawn()) {
            enemyList.add(Enemy(getContext(), player))
        }

        // Update state of each enemy
        for (enemy in enemyList) {
            enemy.update()
        }

        // Update state of each spell
        for (spell in spellList) {
            spell.update()
        }

        // Iterate through enemyList and check for collision between each enemy and the player and all spells
        val iteratorEnemy = enemyList.iterator()
        while (iteratorEnemy.hasNext()) {
            val enemy = iteratorEnemy.next()
            if (Circle.isColliding(enemy, player)) {
                // Eliminar al enemigo si colisiona con el jugador
                iteratorEnemy.remove()
                continue
            }
            val iteratorSpell = spellList.iterator()
            while (iteratorSpell.hasNext()) {
                val spell = iteratorSpell.next()
                if(Circle.isColliding(spell, enemy)){
                    iteratorSpell.remove()
                    iteratorEnemy.remove()
                    break
                }
            }
        }


    }
}