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

    fun getPlayerSpriteArray(): ArrayList<Sprite> {
        val spriteArray = ArrayList<Sprite>()
        spriteArray.add(Sprite( this, rect = Rect(0*64,0,1*64,64)))
        spriteArray.add(Sprite( this, rect = Rect(1*64,6,2*64,64)))
        spriteArray.add(Sprite( this, rect = Rect(2*64,0,3*64,64)))
        return spriteArray
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