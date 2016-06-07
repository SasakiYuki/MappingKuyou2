package com.wacode.yuki.lowaccuracymappingsecond.UI.Setting.SettingRadioDialog

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.RadioButton
import butterknife.bindView
import com.wacode.yuki.lowaccuracymappingsecond.Enum.SettingType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.AccuracyType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.FrequencyType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.ResetType
import com.wacode.yuki.lowaccuracymappingsecond.R
import wacode.yuki.lowaccuracymapping.Enum.ColorType
import wacode.yuki.newontapusha.Utils.PrefUtils

/**
 * Created by yuki on 2016/05/25.
 */
class SettingRadioDialogLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val radio1:RadioButton by bindView(R.id.radioButton1)
    private val radio2:RadioButton by bindView(R.id.radioButton2)
    private val radio3:RadioButton by bindView(R.id.radioButton3)
    private val radio4:RadioButton by bindView(R.id.radioButton4)
    private val radio5:RadioButton by bindView(R.id.radioButton5)

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun bindViews(position:Int){
        when(position){
            0 -> setFrequencyDialog()
            1 -> setAccuracyDialog()
            2 -> setColorDialog()
            3 -> setResetDialog()
        }
    }

    private fun setFrequencyDialog(){
        val list = FrequencyType.values()
        radio1.text = resources.getString(list[0].textResource)
        radio2.text = resources.getString(list[1].textResource)
        radio3.text = resources.getString(list[2].textResource)
        radio4.text = resources.getString(list[3].textResource)
        radio5.text = resources.getString(list[4].textResource)
        checkedRadio(PrefUtils[context,resources.getString(SettingType.FREQUENCY.prefKeyResource),0])
    }

    private fun setAccuracyDialog(){
        val list = AccuracyType.values()
        radio1.text = resources.getString(list[0].textResource)
        radio2.text = resources.getString(list[1].textResource)
        radio3.text = resources.getString(list[2].textResource)
        radio4.text = resources.getString(list[3].textResource)
        radio5.text = resources.getString(list[4].textResource)
        checkedRadio(PrefUtils[context,resources.getString(SettingType.ACCURACY.prefKeyResource),0])
    }

    private fun setColorDialog(){
        val list = ColorType.values()
        radio1.text = resources.getString(list[0].textResource)
        radio2.text = resources.getString(list[1].textResource)
        radio3.text = resources.getString(list[2].textResource)
        radio4.text = resources.getString(list[3].textResource)
        radio5.text = resources.getString(list[4].textResource)
        checkedRadio(PrefUtils[context,resources.getString(SettingType.COLOR.prefKeyResource),0])
    }

    private fun setResetDialog(){
        set3RadioVisible()
        val list = ResetType.values()
        radio1.text = resources.getString(list[0].textResource)
        radio2.text = resources.getString(list[1].textResource)
        radio3.text = resources.getString(list[2].textResource)
        checkedRadio(PrefUtils[context,resources.getString(SettingType.RESET.prefKeyResource),0])
    }

    private fun set3RadioVisible(){
        radio4.visibility = GONE
        radio5.visibility = GONE
    }

    private fun checkedRadio(resource:Int){
        when(resource){
            FrequencyType.MIN30.milliSecond-> radio1.isChecked = true
            FrequencyType.HOUR1.milliSecond -> radio2.isChecked = true
            FrequencyType.HOUR2.milliSecond -> radio3.isChecked = true
            FrequencyType.HOUR3.milliSecond -> radio4.isChecked = true
            FrequencyType.HOUR5.milliSecond -> radio5.isChecked = true
            AccuracyType.MOREWIDE.area -> radio1.isChecked = true
            AccuracyType.WIDE.area -> radio2.isChecked = true
            AccuracyType.NORMAL.area -> radio3.isChecked = true
            AccuracyType.NARROW.area -> radio4.isChecked = true
            AccuracyType.MORENARROW.area -> radio5.isChecked = true
            ColorType.RED.color -> radio1.isChecked = true
            ColorType.GREEN.color -> radio2.isChecked = true
            ColorType.BLUE.color -> radio3.isChecked = true
            ColorType.YELLOW.color -> radio4.isChecked = true
            ColorType.PURPLE.color -> radio5.isChecked = true
            ResetType.DAY.key -> radio1.isChecked = true
            ResetType.WEEK.key -> radio2.isChecked = true
            ResetType.MONTH.key -> radio3.isChecked = true
        }
    }
    
}