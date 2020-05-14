package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16) : String = this.dropLast(this.length - value).trimEnd() + "..."

fun String.stripHtml() : String{
    var str = this

    var clearStr = ""

    while (!str.isNullOrBlank()) {
        val head = str.substringBefore("<")
        val tail = str.substringAfter(">")

        clearStr += head

        str = tail
    }
    val pattern = "\\s+".toRegex()

    return  clearStr.replace(pattern, " ")
}
