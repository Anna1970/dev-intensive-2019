package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        if (fullName.isNullOrBlank()) return  null to null

        val parts : List<String> = fullName.trim().split(" ")

        val firstName = parts.getOrNull(0)
        val lastName  = parts.getOrNull(1)

        return  firstName to lastName
    }

    fun transliteration(payload: String?, devider:String = " "): String {

        if (payload != null){

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

            val parts : List<String> = payload.trim().split(" ")

            var  replaced = ""

            for(part in parts)
            {
                val isUpp = part.get(0).isUpperCase()

                var str = map.entries.fold(part){
                                acc, (key, value) -> acc.replace(key, value, true)
                            }
                if (isUpp) {
                    str =  str.first().toUpperCase() + str.drop(1)
                }
                if (!replaced.isNullOrEmpty()) str = devider + str

                replaced +=  str
            }

            return replaced

        } else  return ""
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName.isNullOrEmpty() && lastName.isNullOrEmpty()) return null

        val fn = firstName?.trim()?.firstOrNull()?:""
        val ln = lastName?.trim()?.firstOrNull()?:""

        val str = fn.toString() + ln.toString()

        return str.toUpperCase()
    }
}