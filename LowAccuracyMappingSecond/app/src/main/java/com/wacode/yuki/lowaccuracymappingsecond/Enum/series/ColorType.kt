package wacode.yuki.lowaccuracymapping.Enum

import com.wacode.yuki.lowaccuracymappingsecond.R

/**
 * Created by Yuki on 2016/04/27.
 */
enum class ColorType (val textResource:Int, val color: Int){
    RED(R.string.setting_color_red,0x20FF0000),
    GREEN(R.string.setting_color_green,0x20008000),
    BLUE(R.string.setting_color_blue,0x200000ff),
    YELLOW(R.string.setting_color_yellow,0x20ffff00),
    PURPLE(R.string.setting_color_purple,0x20800080)
}