package com.wacode.yuki.lowaccuracymappingsecond.Enum.series

import com.wacode.yuki.lowaccuracymappingsecond.R

/**
 * Created by yuki on 2016/05/23.
 */
enum class AccuracyType(val textResource:Int,val area:Int) {
    MOREWIDE(R.string.setting_area_morewide,500),
    WIDE(R.string.setting_area_wide,400),
    NORMAL(R.string.setting_area_normal,300),
    NARROW(R.string.setting_area_narrow,200),
    MORENARROW(R.string.setting_area_morenarrow,100)
}