package com.wacode.yuki.lowaccuracymappingsecond.UI.Setting.SettingCardRecycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.wacode.yuki.lowaccuracymappingsecond.Enum.SettingType
import com.wacode.yuki.lowaccuracymappingsecond.R
import com.wacode.yuki.lowaccuracymappingsecond.UI.Setting.SettingActivity

import wacode.yuki.newontapusha.Utils.PrefUtils

/**
 * Created by yuki on 2016/05/24.
 */
class SettingCardRecyclerAdapter(val context: Context,private val resource:Int,private val objects:Array<SettingType>) : RecyclerView.Adapter<SettingCardRecyclerAdapter.ViewHolder>() {
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.imageView.setImageResource(objects[p1].imageResource)
        p0.textView_main.text = context.resources.getText(objects[p1].textResource)
        p0.textView_sub.text ="設定中："+PrefUtils[context,context.resources.getString(objects[p1].settingPrefKey),""]
        p0.layout.setOnClickListener {
            val activity = context as SettingActivity
            activity.setDialog(p1)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int, payloads: MutableList<Any>?) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount() = objects.size

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int)  = ViewHolder(layoutInflater.inflate(resource,p0,false))


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imageView:ImageView
        val textView_main:TextView
        val textView_sub:TextView
        val layout:SettingCardRecyclerLayout
        init {
            imageView = v.findViewById(R.id.imageView) as ImageView
            textView_main = v.findViewById(R.id.textView_main) as TextView
            textView_sub = v.findViewById(R.id.textView_sub) as TextView
            layout = v.findViewById(R.id.layout) as SettingCardRecyclerLayout
        }
    }
}