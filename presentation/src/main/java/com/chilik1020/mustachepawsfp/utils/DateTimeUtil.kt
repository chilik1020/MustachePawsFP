package com.chilik1020.mustachepawsfp.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.dateTimeFromStamp(): String {
    val c: Calendar = Calendar.getInstance()
    c.timeInMillis = this * 1000
    val d: Date = c.time
    val sdf = SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault())
    return sdf.format(d)
}

fun Long.dateFromStamp(): String {
    val c: Calendar = Calendar.getInstance()
    c.timeInMillis = this * 1000
    val d: Date = c.time
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(d)
}