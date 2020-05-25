package ru.skillbranch.devintensive.ui.custom
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import ru.skillbranch.devintensive.utils.Utils.textBitmap
import java.util.*
import kotlin.math.abs
import kotlin.math.min
import kotlin.random.Random

private val colors = arrayOf(Color.GREEN, Color.CYAN, Color.BLUE, Color.RED, Color.YELLOW)
private val random = Random(Date().time)

class AvatarImageView @JvmOverloads constructor(ctx: Context,
                                                attrs: AttributeSet,
                                                defStyle: Int = 0
) : CircleImageView(ctx, attrs, defStyle) {

    private var initials: String = "??"
    private val backgroundRandomColor = colors[abs(random.nextInt()) % colors.size]

    fun setInitials(newInitials: String) {
        initials = newInitials
        setInitialsBitmap(width, height)
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        setInitialsBitmap(w, h)
        super.onSizeChanged(w, h, oldw, oldh)
    }

    private fun setInitialsBitmap(w: Int, h: Int) {
        if (w > 0 && h > 0) setImageBitmap(textBitmap(w, h, initials, backgroundRandomColor, (min(w, h) * 0.4f).toInt()))
    }
}