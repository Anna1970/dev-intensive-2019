package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR



fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time

    time += when(units){
        TimeUnits.SECOND-> value * SECOND
        TimeUnits.MINUTE-> value * MINUTE
        TimeUnits.HOUR-> value * HOUR
        TimeUnits.DAY-> value * DAY
    }

    this.time = time

    return this
}

fun Date.humanizeDiff(date:Date = Date()) : String{

    val diffTime = (date.time - this.time)

    val map = mapOf<IntRange,String>(
                (0..1) to "только что",
                (1..45) to "несколько секунд назад",
                (45..75) to "минуту назад",
                (75..2700) to "${TimeUnits.MINUTE.plural((diffTime/MINUTE).toInt())} назад",
                (2700..4500) to "час назад",
                (4500..79200) to "${TimeUnits.HOUR.plural((diffTime/HOUR).toInt())} назад",
                (79200..93600) to "день назад",
                (93600..31104000) to "${TimeUnits.DAY.plural((diffTime/DAY).toInt())} назад",
                (31104000..Int.MAX_VALUE) to "более года назад"
    )

    for ((key, value) in map){
        if (diffTime/SECOND in key) {
            return value
        }
    }
    return "<<< ${date.format()} >>>  $diffTime"

}

enum class TimeUnits{
    SECOND {
      override fun plural(value: Int) : String
      {
          var s = "$value "
           when(value){
               0 -> s += "секунд"
               1 -> s += "секунда"
               2,3,4 -> s += "секунды"
               else -> s += "секунд"
           }

          return s
        }
    },
    MINUTE{
        override fun plural(value: Int):String
        {
            var s = "$value "
            when(value){
                0 -> s += "минут"
                1 -> s += "минута"
                2,3,4 -> s += "минуты"
                else -> s += "минут"
            }

            return s
        }
},
    HOUR {
        override  fun plural(value: Int): String {
            var s = "$value "
            when(value){
                0 -> s += "часов"
                1 -> s += "час"
                2,3,4 -> s += "часа"
                else -> s += "часов"
            }

            return s
        }
    },
    DAY {
        override fun plural(value: Int): String {
            var s = "$value "
            when(value){
                0 -> s += "дней"
                1 -> s += "день"
                2,3,4 -> s += "дня"
                else -> s += "дней"
            }

            return s
        }
    };

    abstract fun plural(value: Int = 1) :String
}