import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat
import com.android.example.androidstudio2dgame.GameDisplay
import com.android.example.androidstudio2dgame.gamepanel.Performance
import com.android.example.androidstudio2dgame.`object`.Enemy
import com.android.example.androidstudio2dgame.gamepanel.Joystick
import com.android.example.androidstudio2dgame.`object`.Player
import com.android.example.androidstudio2dgame.R
import com.android.example.androidstudio2dgame.`object`.Spell
import com.android.example.androidstudio2dgame.`object`.Circle
import com.android.example.androidstudio2dgame.gamepanel.GameOver
import com.example.androidstudio2dgamedevelopment.GameLoop

class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val joystick: Joystick
    private val player: Player
    private var gameLoop: GameLoop
    private val enemyList: MutableList<Enemy> = mutableListOf()
    private val spellList: MutableList<Spell> = mutableListOf()
    private var joystickPointerId: Int = 0
    private var numberOfSpellsToCast: Int = 0
    private val gameOver: GameOver
    private val performance: Performance
    private lateinit var gameDisplay: GameDisplay


    init {
        // Obtener SurfaceHolder y añadir el callback
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)

        // Inicializar GameLoop
        gameLoop = GameLoop(this, surfaceHolder)


        // Initialize game panels
        performance = Performance(getContext(), gameLoop)
        gameOver = GameOver(getContext())
        joystick = Joystick(275, 700, 70, 40)

        //Inicializar objetos del juego
        val spriteSheet = SpriteSheet(context)
        player = Player(getContext(), joystick, 500.0, 500.0, 32.0, spriteSheet.getPlayerSprite())

        // Initialize game display and center it around the player
        val displayMetrics = DisplayMetrics()
        (getContext() as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        gameDisplay = GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player)

        // Hacer que la vista sea focalizable
        isFocusable = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Manejar las acciones del evento táctil
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN  -> {
                if (joystick.getIsPressed()) {
                    // Joystick already pressed before this event -> cast spell
                    numberOfSpellsToCast++
                } else if (joystick.isPressed(event.x.toDouble(), event.y.toDouble())) {
                    // Joystick is pressed in this event -> setIsPressed(true) and store ID
                    joystickPointerId = event.getPointerId(event.actionIndex)
                    joystick.setIsPressed(true) // Marca que el joystick está presionado
                } else {
                    // Joystick was not previously, and is not pressed in this event -> cast spell
                    numberOfSpellsToCast++
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
        Log.d("Game.kt", "surfaceCreated()")
        gameLoop.startLoop()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.d("Game.kt", "surfaceChanged()")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.d("Game.kt", "surfaceDestroyed()")
        gameLoop.stopLoop()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        player.draw(canvas, gameDisplay)

        for (enemy in enemyList) {
            enemy.draw(canvas, gameDisplay)
        }

        for (spell in spellList) {
            spell.draw(canvas, gameDisplay)
        }

        // Draw game panels
        joystick.draw(canvas)
        performance.draw(canvas)

        // Draw Game Over if the player is dead
        if (player.retrieveHealthPoints() <= 0) {
            gameOver.draw(canvas)
        }
    }

    fun update() {

        // Stop updating the game if the player is dead
        if ( player.retrieveHealthPoints() <= 0) {
            return
        }


        joystick.update()
        player.update()

        // Spawn enemy if is time to spawn new enemies
        if (Enemy.readyToSpawn()) {
            enemyList.add(Enemy(getContext(), player))
        }

        while (numberOfSpellsToCast > 0) {
            spellList.add(Spell(context, player))
            numberOfSpellsToCast--
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
                player.settingHealthPoints(player.retrieveHealthPoints()-1.0)
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

        gameDisplay.update()

    }

    fun pause() {
        gameLoop.stopLoop()
    }

    fun setGameLoop(newGameLoop: GameLoop) {
        gameLoop = newGameLoop
    }

}