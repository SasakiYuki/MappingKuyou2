package com.wacode.yuki.lowaccuracymappingsecond.UI.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import butterknife.bindView
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.FrequencyType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.SettingType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.AccuracyType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.ResetType
import com.wacode.yuki.lowaccuracymappingsecond.R
import com.wacode.yuki.lowaccuracymappingsecond.UI.Map.MapsActivity
import com.wacode.yuki.lowaccuracymappingsecond.UI.Setting.SettingActivity
import wacode.yuki.lowaccuracymapping.Enum.ColorType
import wacode.yuki.lowaccuracymapping.Service.MappingService
import wacode.yuki.newontapusha.Utils.PrefUtils

class MainActivity : AppCompatActivity() {
    private val textView_setting: TextView by bindView(R.id.textView_setting)
    private val textView_map:TextView by bindView(R.id.textView_map)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isFirstStart()) InitPref()
        setOnClick()
        startService(Intent(this,MappingService::class.java))
    }

    private fun setOnClick(){
        var intent:Intent
        textView_setting.setOnClickListener {
            intent = Intent(this,SettingActivity::class.java)
            startActivity(intent)
        }
        textView_map.setOnClickListener {
            intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun InitPref(){
        PrefUtils.put(this,resources.getString(SettingType.ACCURACY.prefKeyResource), AccuracyType.NORMAL.area)
        PrefUtils.put(this,resources.getString(SettingType.FREQUENCY.prefKeyResource), FrequencyType.HOUR2.milliSecond)
        PrefUtils.put(this,resources.getString(SettingType.COLOR.prefKeyResource), ColorType.BLUE.color)
        PrefUtils.put(this,resources.getString(SettingType.RESET.prefKeyResource), ResetType.WEEK.key)

        PrefUtils.put(this,resources.getString(SettingType.ACCURACY.settingPrefKey),resources.getString(AccuracyType.NORMAL.textResource))
        PrefUtils.put(this,resources.getString(SettingType.FREQUENCY.settingPrefKey),resources.getString(FrequencyType.HOUR2.textResource))
        PrefUtils.put(this,resources.getString(SettingType.COLOR.settingPrefKey),resources.getString(ColorType.BLUE.textResource))
        PrefUtils.put(this,resources.getString(SettingType.RESET.settingPrefKey),resources.getString(ResetType.WEEK.textResource))

        PrefUtils.put(this, PREFKEY_INIT,false)
    }


    private fun isFirstStart() = PrefUtils[this, PREFKEY_INIT,true]

    companion object{
        private val PREFKEY_INIT = "prefKey_init"
    }
}
