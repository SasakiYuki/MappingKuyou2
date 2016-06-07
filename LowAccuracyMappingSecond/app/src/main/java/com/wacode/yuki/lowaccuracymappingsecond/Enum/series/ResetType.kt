package com.wacode.yuki.lowaccuracymappingsecond.Enum.series

import com.wacode.yuki.lowaccuracymappingsecond.R

/**
 * Created by yuki on 2016/05/23.
 */
enum class ResetType (val textResource:Int,val key:Int){
    DAY(R.string.setting_data_day,1),
    WEEK(R.string.setting_data_week,2),
    MONTH(R.string.setting_data_month,3),
}