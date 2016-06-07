package com.wacode.yuki.lowaccuracymappingsecond.Utils

import android.content.Context
import java.util.*

/**
 * Created by yuki on 2016/05/27.
 */
object CalendarUtils {

    private fun getCalendar() = Calendar.getInstance()

    fun getCurrentDate():Int{
        val calendar = getCalendar()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) +1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return day+month*100+year*10000
    }

    fun getEndDayForWeek():Int{
        val calendar = getCalendar()
        calendar.add(Calendar.DATE,7)
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return day+month*100+year*10000
    }

    fun getEndDayForMonth():Int{
        val calendar = getCalendar()
        calendar.add(Calendar.MONTH,1)
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return day+month*100+year*10000
    }

    fun getYear() = getCalendar().get(Calendar.YEAR)

    fun getMonth() = getCalendar().get(Calendar.MONTH)

    fun getDay() = getCalendar().get(Calendar.DAY_OF_MONTH)
}
