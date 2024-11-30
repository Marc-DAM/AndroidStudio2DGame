import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.android.example.androidstudio2dgame.R
import com.android.example.androidstudio2dgame.graphics.Sprite

class SpriteSheet(context: Context) {

    private val bitmap: Bitmap

    init {
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inScaled = false
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.sprite_sheet, bitmapOptions)
    }

    fun getPlayerSprite(): Sprite {
        return Sprite( this, rect = Rect(0,0,64,64))
    }

    fun getBitmap(): Bitmap {
        return bitmap
    }
}