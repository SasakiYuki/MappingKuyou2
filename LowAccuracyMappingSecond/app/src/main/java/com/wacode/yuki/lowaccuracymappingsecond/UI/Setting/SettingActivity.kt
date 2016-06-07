package com.wacode.yuki.lowaccuracymappingsecond.UI.Setting

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.RadioGroup
import butterknife.bindView
import com.wacode.yuki.lowaccuracymappingsecond.Enum.SettingType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.AccuracyType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.FrequencyType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.ResetType
import com.wacode.yuki.lowaccuracymappingsecond.R
import com.wacode.yuki.lowaccuracymappingsecond.UI.Setting.SettingCardRecycler.SettingCardRecyclerView
import com.wacode.yuki.lowaccuracymappingsecond.UI.Setting.SettingRadioDialog.SettingRadioDialogLayout
import wacode.yuki.lowaccuracymapping.Enum.ColorType
import wacode.yuki.newontapusha.Utils.PrefUtils

class SettingActivity : AppCompatActivity() {

    private val recycerView:SettingCardRecyclerView by bindView(R.id.recyclerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setToolbar()
    }

    private fun setToolbar(){
        val toolBar = findViewById(R.id.toolBar) as Toolbar
        toolBar.title = resources.getString(R.string.title_setting)
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    fun setDialog(position:Int){
         val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
         val view = layoutInflater.inflate(R.layout.layout_dialog_radio5,null) as SettingRadioDialogLayout

         val radioGroup = view.findViewById(R.id.radioGroup) as RadioGroup

         val builder = AlertDialog.Builder(this)
         builder.setTitle("設定の変更")
         builder.setView(view)
         builder.setPositiveButton("設定", DialogInterface.OnClickListener { dialogInterface, i ->
            setSetting(position,checkRadioGroupPosition(radioGroup.checkedRadioButtonId))
         })
         builder.create().show()
         view.bindViews(position)
    }

    private fun setSetting(position: Int,i:Int){
        when(position){
            0 -> setFrequency(i)
            1 -> setAccuracy(i)
            2 -> setColorType(i)
            3 -> setResetType(i)
        }
        recycerView.setAdapters()
    }

    private fun checkRadioGroupPosition(position: Int):Int{
        when(position){
            R.id.radioButton1 -> return 0
            R.id.radioButton2 -> return 1
            R.id.radioButton3 -> return 2
            R.id.radioButton4 -> return 3
            R.id.radioButton5 -> return 4
        }
        return 0
    }

    private fun setFrequency(i: Int){
        when(i){
            0 -> PrefUtils.put(this,resources.getString(SettingType.FREQUENCY.prefKeyResource),FrequencyType.MIN30.milliSecond)
            1 -> PrefUtils.put(this,resources.getString(SettingType.FREQUENCY.prefKeyResource),FrequencyType.HOUR1.milliSecond)
            2 -> PrefUtils.put(this,resources.getString(SettingType.FREQUENCY.prefKeyResource),FrequencyType.HOUR2.milliSecond)
            3 -> PrefUtils.put(this,resources.getString(SettingType.FREQUENCY.prefKeyResource),FrequencyType.HOUR3.milliSecond)
            4 -> PrefUtils.put(this,resources.getString(SettingType.FREQUENCY.prefKeyResource),FrequencyType.HOUR5.milliSecond)
        }
        when(i){
            0 -> PrefUtils.put(this,resources.getString(SettingType.FREQUENCY.settingPrefKey),resources.getString(FrequencyType.MIN30.textResource))
            1 -> PrefUtils.put(this,resources.getString(SettingType.FREQUENCY.settingPrefKey),resources.getString(FrequencyType.HOUR1.textResource))
            2 -> PrefUtils.put(this,resources.getString(SettingType.FREQUENCY.settingPrefKey),resources.getString(FrequencyType.HOUR2.textResource))
            3 -> PrefUtils.put(this,resources.getString(SettingType.FREQUENCY.settingPrefKey),resources.getString(FrequencyType.HOUR3.textResource))
            4 -> PrefUtils.put(this,resources.getString(SettingType.FREQUENCY.settingPrefKey),resources.getString(FrequencyType.HOUR5.textResource))
        }
    }

    private fun setAccuracy(i: Int){
        when(i){
            0 -> PrefUtils.put(this,resources.getString(SettingType.ACCURACY.prefKeyResource), AccuracyType.MOREWIDE.area)
            1 -> PrefUtils.put(this,resources.getString(SettingType.ACCURACY.prefKeyResource),AccuracyType.WIDE.area)
            2 -> PrefUtils.put(this,resources.getString(SettingType.ACCURACY.prefKeyResource),AccuracyType.NORMAL.area)
            3 -> PrefUtils.put(this,resources.getString(SettingType.ACCURACY.prefKeyResource),AccuracyType.NARROW.area)
            4 -> PrefUtils.put(this,resources.getString(SettingType.ACCURACY.prefKeyResource),AccuracyType.MORENARROW.area)
        }
        when(i){
            0 -> PrefUtils.put(this,resources.getString(SettingType.ACCURACY.settingPrefKey),resources.getString(AccuracyType.MOREWIDE.textResource))
            1 -> PrefUtils.put(this,resources.getString(SettingType.ACCURACY.settingPrefKey),resources.getString(AccuracyType.WIDE.textResource))
            2 -> PrefUtils.put(this,resources.getString(SettingType.ACCURACY.settingPrefKey),resources.getString(AccuracyType.NORMAL.textResource))
            3 -> PrefUtils.put(this,resources.getString(SettingType.ACCURACY.settingPrefKey),resources.getString(AccuracyType.NARROW.textResource))
            4 -> PrefUtils.put(this,resources.getString(SettingType.ACCURACY.settingPrefKey),resources.getString(AccuracyType.MORENARROW.textResource))
        }
    }

    private fun setColorType(i: Int){
        when(i){
            0 -> PrefUtils.put(this,resources.getString(SettingType.COLOR.prefKeyResource), ColorType.RED.color)
            1 -> PrefUtils.put(this,resources.getString(SettingType.COLOR.prefKeyResource),ColorType.GREEN.color)
            2 -> PrefUtils.put(this,resources.getString(SettingType.COLOR.prefKeyResource),ColorType.BLUE.color)
            3 -> PrefUtils.put(this,resources.getString(SettingType.COLOR.prefKeyResource),ColorType.YELLOW.color)
            4 -> PrefUtils.put(this,resources.getString(SettingType.COLOR.prefKeyResource),ColorType.PURPLE.color)
        }
        when(i){
            0 -> PrefUtils.put(this,resources.getString(SettingType.COLOR.settingPrefKey),resources.getString(ColorType.RED.textResource))
            1 -> PrefUtils.put(this,resources.getString(SettingType.COLOR.settingPrefKey),resources.getString(ColorType.GREEN.textResource))
            2 -> PrefUtils.put(this,resources.getString(SettingType.COLOR.settingPrefKey),resources.getString(ColorType.BLUE.textResource))
            3 -> PrefUtils.put(this,resources.getString(SettingType.COLOR.settingPrefKey),resources.getString(ColorType.YELLOW.textResource))
            4 -> PrefUtils.put(this,resources.getString(SettingType.COLOR.settingPrefKey),resources.getString(ColorType.PURPLE.textResource))
        }
    }

    private fun setResetType(i: Int){
        when(i){
            0 -> PrefUtils.put(this,resources.getString(SettingType.RESET.prefKeyResource), ResetType.DAY.key)
            1 -> PrefUtils.put(this,resources.getString(SettingType.RESET.prefKeyResource),ResetType.WEEK.key)
            2 -> PrefUtils.put(this,resources.getString(SettingType.RESET.prefKeyResource),ResetType.MONTH.key)
        }
        when(i){
            0 -> PrefUtils.put(this,resources.getString(SettingType.RESET.settingPrefKey),resources.getString(ResetType.DAY.textResource))
            1 -> PrefUtils.put(this,resources.getString(SettingType.RESET.settingPrefKey),resources.getString(ResetType.WEEK.textResource))
            2 -> PrefUtils.put(this,resources.getString(SettingType.RESET.settingPrefKey),resources.getString(ResetType.MONTH.textResource))
        }
    }
}
