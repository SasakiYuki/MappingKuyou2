package com.wacode.yuki.lowaccuracymappingsecond.UI.Setting.SettingCardRecycler

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.wacode.yuki.lowaccuracymappingsecond.Enum.SettingType
import com.wacode.yuki.lowaccuracymappingsecond.R

/**
 * Created by yuki on 2016/05/24.
 */
class SettingCardRecyclerView(context: Context?, attrs: AttributeSet?) : RecyclerView(context, attrs) {
    override fun onFinishInflate() {
        super.onFinishInflate()
        setAdapters()
    }

    fun setAdapters(){
        layoutManager = LinearLayoutManager(context)
        val adapter = SettingCardRecyclerAdapter(context, R.layout.layout_setting_recycler,SettingType.values())
        setAdapter(adapter)
    }
}