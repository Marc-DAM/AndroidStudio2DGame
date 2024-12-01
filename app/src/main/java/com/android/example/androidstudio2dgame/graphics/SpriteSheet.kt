import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.android.example.androidstudio2dgame.R
import com.android.example.androidstudio2dgame.graphics.Sprite

class SpriteSheet(context: Context) {

    companion object {
        private const val SPRITE_WIDTH_PIXELS = 64
        private const val SPRITE_HEIGHT_PIXELS = 64
    }

    private val bitmap: Bitmap

    init {
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inScaled = false
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.sprite_sheet, bitmapOptions)
    }

    fun getPlayerSpriteArray(): Map<String, ArrayList<Sprite>> {
        val spriteMap = mutableMapOf<String, ArrayList<Sprite>>()

        // Sprites mirando hacia abajo (fila 1)
        spriteMap["DOWN"] = arrayListOf(
            Sprite(this, rect = Rect(0 * 64, 0 * 64, 1 * 64, 1 * 64)), // Idle abajo
            Sprite(this, rect = Rect(1 * 64, 0 * 64, 2 * 64, 1 * 64)), // Movimiento 1 abajo
            Sprite(this, rect = Rect(2 * 64, 0 * 64, 3 * 64, 1 * 64))  // Movimiento 2 abajo
        )

        // Sprites mirando hacia arriba (fila 3)
        spriteMap["UP"] = arrayListOf(
            Sprite(this, rect = Rect(0 * 64, 2 * 64, 1 * 64, 3 * 64)), // Idle arriba
            Sprite(this, rect = Rect(1 * 64, 2 * 64, 2 * 64, 3 * 64)), // Movimiento 1 arriba
            Sprite(this, rect = Rect(2 * 64, 2 * 64, 3 * 64, 3 * 64))  // Movimiento 2 arriba
        )

        // Sprites mirando hacia la derecha (fila 4)
        spriteMap["RIGHT"] = arrayListOf(
            Sprite(this, rect = Rect(0 * 64, 3 * 64, 1 * 64, 4 * 64)), // Idle derecha
            Sprite(this, rect = Rect(1 * 64, 3 * 64, 2 * 64, 4 * 64)), // Movimiento 1 derecha
            Sprite(this, rect = Rect(2 * 64, 3 * 64, 3 * 64, 4 * 64))  // Movimiento 2 derecha
        )

        // Sprites mirando hacia la izquierda (fila 5)
        spriteMap["LEFT"] = arrayListOf(
            Sprite(this, rect = Rect(0 * 64, 4 * 64, 1 * 64, 5 * 64)), // Idle izquierda
            Sprite(this, rect = Rect(1 * 64, 4 * 64, 2 * 64, 5 * 64)), // Movimiento 1 izquierda
            Sprite(this, rect = Rect(2 * 64, 4 * 64, 3 * 64, 5 * 64))  // Movimiento 2 izquierda
        )

        return spriteMap
    }

    fun getSpellSpriteArray(): Map<String, ArrayList<Sprite>> {
        val spriteArray = ArrayList<Sprite>()
        spriteArray.add(Sprite(this, rect = Rect(3 * 64, 0, 4 * 64, 64))) // Sprite 1 del spell
        spriteArray.add(Sprite(this, rect = Rect(4 * 64, 0, 5 * 64, 64))) // Sprite 2 del spell
        return mapOf("DEFAULT" to spriteArray)

    }

    fun getBitmap(): Bitmap {
        return bitmap
    }


    private fun getSpriteByIndex(idxRow: Int, idxCol: Int): Sprite {
        return Sprite(this, Rect(
            idxCol * SPRITE_WIDTH_PIXELS,
            idxRow * SPRITE_HEIGHT_PIXELS,
            (idxCol + 1) * SPRITE_WIDTH_PIXELS,
            (idxRow + 1) * SPRITE_HEIGHT_PIXELS
             )
        )
    }

    fun getWaterSprite(): Sprite {
        return getSpriteByIndex(1, 0)
    }
    fun getLavaSprite(): Sprite {
        return getSpriteByIndex(1, 1)
    }
    fun getGroundSprite(): Sprite {
        return getSpriteByIndex(1, 2)
    }
    fun getGrassSprite(): Sprite {
        return getSpriteByIndex(1, 3)
    }
    fun getTreeSprite(): Sprite {
        return getSpriteByIndex(1, 4)
    }
}