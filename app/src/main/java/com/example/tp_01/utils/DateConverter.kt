package com.example.tp_01.utils

import androidx.databinding.InverseMethod
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date

object DateConverter {

    @JvmStatic @InverseMethod("stringToDate")
        fun dateToString(date: Date): String {
           var formatter = SimpleDateFormat("dd/MM/yyyy")
            return formatter.format(date)
        }

    @JvmStatic
    fun stringToDate(date: String): Date? {
        var formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.parse(date)
    }

    @TypeConverter
    fun fromTimestamp(value : Long) : Date {
        return Date(value)
    }
    @TypeConverter
    fun dateToTimestamp(date : Date) : Long {
        return date.time
    }

}