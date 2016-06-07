package com.wacode.yuki.lowaccuracymappingsecond.UI.Map.HistoryDialog

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.bindView
import com.wacode.yuki.lowaccuracymappingsecond.R
import com.wacode.yuki.lowaccuracymappingsecond.UI.Map.MapsActivity
import com.wacode.yuki.lowaccuracymappingsecond.Utils.CalendarUtils

/**
 * Created by yuki on 2016/05/31.
 */
class HistoryDialogLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val startLayout:LinearLayout by bindView(R.id.startDateLayout)
    private val endLayout:LinearLayout by bindView(R.id.endDateLayout)
    private val textView_startYear:TextView by bindView(R.id.textView_startYear)
    private val textView_startMonth:TextView by bindView(R.id.textView_startMonth)
    private val textView_startDay:TextView by bindView(R.id.textView_startDay)
    private val textView_endYear:TextView by bindView(R.id.textView_endYear)
    private val textView_endMonth:TextView by bindView(R.id.textView_endMonth)
    private val textView_endDay:TextView by bindView(R.id.textView_endDay)
    private val button_normal:Button by bindView(R.id.button_normal)
    private val button_set:Button by bindView(R.id.button_set)
    private val button_cancel:Button by bindView(R.id.button_cancel)

    private var startDate = 0
    private var endDate = 100000000

    override fun onFinishInflate() {
        super.onFinishInflate()
        setOnClickListener()
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
    }

    private fun setOnClickListener(){
        startLayout.setOnClickListener { setDatePickerDialog(startDateSetListener) }
        endLayout.setOnClickListener { setDatePickerDialog(endDateSetListener) }
        button_cancel.setOnClickListener { visibility = GONE }
        button_normal.setOnClickListener {
            visibility = GONE
            val activity = context as MapsActivity
            activity.setNormalMapping()
        }
        button_set.setOnClickListener {
            visibility = GONE
            val activity = context as MapsActivity
            activity.setExtraMapping(startDate,endDate)
        }
    }

    private fun setDatePickerDialog(dpd:DatePickerDialog.OnDateSetListener){
        DatePickerDialog(context,dpd,CalendarUtils.getYear(),CalendarUtils.getMonth(),CalendarUtils.getDay()).show()
    }

    private val startDateSetListener:DatePickerDialog.OnDateSetListener
        get() = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            textView_startDay.text = day.toString()
            textView_startMonth.text = (month+1).toString()
            textView_startYear.text = year.toString()
            startDate = day+month*100+year*10000
        }

    private val endDateSetListener:DatePickerDialog.OnDateSetListener
        get() = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            textView_endDay.text = day.toString()
            textView_endMonth.text = (month+1).toString()
            textView_endYear.text = year.toString()
            endDate = day+month*100+year*10000
        }


}