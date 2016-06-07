package com.wacode.yuki.lowaccuracymappingsecond.Enum

import com.wacode.yuki.lowaccuracymappingsecond.R

/**
 * Created by yuki on 2016/05/23.
 */
enum class SettingType (val textResource:Int,val prefKeyResource:Int,val imageResource:Int,val settingPrefKey:Int){
    FREQUENCY(R.string.type_setting_frequency,R.string.prefKey_frequency,R.mipmap.time,R.string.prefKey_s_frequency),
    ACCURACY(R.string.type_setting_accuracy,R.string.prefKey_accuracy,R.mipmap.hirosa,R.string.prefKey_s_accuracy),
    COLOR(R.string.type_setting_color,R.string.prefKey_color,R.mipmap.iro,R.string.prefKey_s_color),
    RESET(R.string.type_setting_reset,R.string.prefKey_reset,R.mipmap.reset,R.string.prefKey_s_reset)
}
