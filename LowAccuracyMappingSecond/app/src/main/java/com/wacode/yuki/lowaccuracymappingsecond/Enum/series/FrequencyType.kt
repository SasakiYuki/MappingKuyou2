package com.wacode.yuki.lowaccuracymappingsecond.Enum.series

import com.wacode.yuki.lowaccuracymappingsecond.R

/**
 * Created by yuki on 2016/05/23.
 */
enum class FrequencyType (val textResource:Int,val milliSecond:Int){
    MIN30(R.string.setting_time_30min,1800000),
    HOUR1(R.string.setting_time_1hour,3600000),
    HOUR2(R.string.setting_time_2hour,7200000),
    HOUR3(R.string.setting_time_3hour,10800000),
    HOUR5(R.string.setting_time_5hour,18000000)

}