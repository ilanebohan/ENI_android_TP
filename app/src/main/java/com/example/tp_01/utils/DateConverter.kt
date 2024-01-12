package com.example.tp_01.utils

import androidx.databinding.InverseMethod
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

}