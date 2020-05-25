package ru.skillbranch.devintensive.utils

import android.graphics.*
import androidx.annotation.ColorInt
import java.lang.Double.min
import kotlin.math.min

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        if (fullName.isNullOrBlank()) return  null to null

        val parts : List<String> = fullName.trim().split(" ")

        val firstName = parts.getOrNull(0)
        val lastName  = parts.getOrNull(1)

        return  firstName to lastName
    }

    fun transliteration(payload: String?, devider:String = " "): String {

        if (payload.isNullOrEmpty()) return ""

        val map = mapOf<String,String>(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya")

        val parts : List<String?> = payload?.trim().split(" ")

        var  replaced = ""

        if (parts.isNullOrEmpty()) return ""

        for(part in parts)
        {
            val isUpp = part!![0].isUpperCase()

            var str = map.entries.fold(part!!){
                            acc, (key, value) -> acc.replace(key, value, true)
                        }
            if (isUpp) {
                str =  str.first().toUpperCase() + str.drop(1)
            }
            if (!replaced.isNullOrEmpty()) str = devider + str

            replaced +=  str
        }

        return replaced
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName.isNullOrEmpty() && lastName.isNullOrEmpty()) return null
        return  (firstName?.trim()?.firstOrNull()?:"" + lastName?.trim()?.firstOrNull()).toString().toUpperCase()
    }

    fun validateURL(url: CharSequence?): Boolean {
        val wrongNames = listOf(
            "enterprise",
            "features",
            "topics",
            "collections",
            "trending",
            "events",
            "marketplace",
            "pricing",
            "nonprofit",
            "customer-stories",
            "security",
            "login",
            "join"
        ).joinToString("|")

        val pattern = Regex("""^(https://)?(www\.)?github\.com/(?!($wrongNames)/?$)[\-\w]+/?$""")
        return url.isNullOrBlank() || pattern.matches(url)
    }

    fun textBitmap(
        width: Int,
        height: Int,
        text: String = "",
        @ColorInt bgColor: Int = Color.BLACK,
        textSize: Int = (min(width, height) * 0.6f).toInt(),
        @ColorInt textColor: Int = Color.WHITE
    ): Bitmap {
        val bitmap: Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        canvas.drawColor(bgColor)

        if (text.isNotEmpty()) {
            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            paint.textSize = textSize.toFloat()
            paint.color = textColor
            paint.textAlign = Paint.Align.CENTER

            val textBounds = Rect()
            paint.getTextBounds(text, 0, text.length, textBounds)

            val backgroundBounds = RectF()
            backgroundBounds.set(0f, 0f, width.toFloat(), height.toFloat())

            val textBottom = backgroundBounds.centerY() - textBounds.exactCenterY()
            canvas.drawText(text, backgroundBounds.centerX(), textBottom, paint)
        }

        return bitmap
    }
}