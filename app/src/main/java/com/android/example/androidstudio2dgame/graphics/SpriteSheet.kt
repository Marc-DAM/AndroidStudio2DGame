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
}