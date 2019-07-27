package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.humanizeDiff(currDate: Date = Date()): String {
    val diff = currDate.time - this.time
    if (abs(diff) <= SECOND) {
        return "только что"
    }

    if (abs(diff) >= SECOND && abs(diff) <= SECOND * 45) {
        return parseResult(diff, TimeUnits.SECOND, false)
    }

    if (abs(diff) >= SECOND * 45 && abs(diff) <= SECOND * 75) {
        return parseResult(diff, TimeUnits.MINUTE, true)
    }

    if (abs(diff) >= 75 * SECOND && abs(diff) <= MINUTE * 45) {
        return parseResult(diff, TimeUnits.MINUTE, false)
    }

    if (abs(diff) >= MINUTE * 45 && abs(diff) <= MINUTE * 75) {
        return parseResult(diff, TimeUnits.HOUR, true)
    }

    if (abs(diff) >= MINUTE * 75 && abs(diff) <= HOUR * 22) {
        return parseResult(diff, TimeUnits.HOUR, false)
    }

    if (abs(diff) >= HOUR * 22 && abs(diff) <= HOUR * 26) {
        return parseResult(diff, TimeUnits.DAY, true)
    }

    if (abs(diff) >= HOUR * 26 && abs(diff) <= DAY * 360) {
        return parseResult(diff, TimeUnits.DAY, false)
    }

    if (abs(diff) >= DAY * 360) {
        if (diff > 0) return "более года назад"
        return "более чем через год"
    }
    return ""
}

private fun parseResult(diff: Long, unit: TimeUnits, oneUnit: Boolean): String {
    when (unit) {
        TimeUnits.DAY -> if (diff > 0) {
            if (oneUnit) {
                return "день назад"
            } else {
                val result = diff / DAY
                if (result == 1L) {
                    return "$result день назад"
                } else if ((result%10 in 2L..4L || result in 2L..4L)&& result%100 !in 12L..14L) {
                    return "$result дня назад"
                } else {
                    return "$result дней назад"
                }
            }
        } else {
            if (oneUnit) {
                return "через день"
            } else {
                val result = abs(diff) / DAY
                if (result == 1L) {
                    return "через $result день"
                } else if ((result%10 in 2L..4L || result in 2L..4L)&& result%100 !in 12L..14L) {
                    return "через $result дня"
                } else {
                    return "через $result дней"
                }
            }
        }
        TimeUnits.HOUR -> if (diff > 0) {
            if (oneUnit) {
                return "час назад"
            } else {
                val result = diff / HOUR
                if (result == 1L) {
                    return "$result час назад"
                } else if ((result%10 in 2L..4L || result in 2L..4L)&& result%100 !in 12L..14L) {
                    return "$result часа назад"
                } else {
                    return "$result часов назад"
                }
            }
        } else {
            if (oneUnit) {
                return "через час"
            } else {
                val result = abs(diff) / HOUR
                if (result == 1L) {
                    return "через $result час"
                } else if ((result%10 in 2L..4L || result in 2L..4L)&& result%100 !in 12L..14L) {
                    return "через $result часа"
                } else {
                    return "через $result часов"
                }
            }
        }
        TimeUnits.MINUTE -> if (diff > 0) {
            if (oneUnit) {
                return "минуту назад"
            } else {
                val result = diff / MINUTE
                if (result == 1L) {
                    return "$result минуту назад"
                } else if ((result%10 in 2L..4L || result in 2L..4L)&& result%100 !in 12L..14L) {
                    return "$result минуты назад"
                } else {
                    return "$result минут назад"
                }
            }
        } else {
            if (oneUnit) {
                return "через минуту"
            } else {
                val result = abs(diff) / MINUTE
                if (result == 1L) {
                    return "через $result минуту"
                } else if ((result%10 in 2L..4L || result in 2L..4L)&& result%100 !in 12L..14L) {
                    return "через $result минуты"
                } else {
                    return "через $result минут"
                }
            }
        }
        TimeUnits.SECOND -> if (diff > 0) {
            return "несколько секунд назад"
        } else {
            return "через несколько секунд"
        }

    }
}