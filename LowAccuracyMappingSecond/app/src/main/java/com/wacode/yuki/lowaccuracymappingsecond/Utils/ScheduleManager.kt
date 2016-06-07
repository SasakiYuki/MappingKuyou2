package wacode.yuki.lowaccuracymapping.UI.Main

import android.content.Context
import com.wacode.yuki.lowaccuracymappingsecond.Enum.SettingType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.ResetType
import wacode.yuki.newontapusha.Utils.PrefUtils
import java.util.*

/**
 * Created by yuki on 2016/05/20.
 */
object ScheduleManager {

    private val PREFKEY_ENDDATE ="prefKey_endDate"

     fun checkDate(context: Context):Int{
        val calendar = Calendar.getInstance()
        var endDate = getEndDate(context)
        return if(parseCalendar(calendar) >= endDate) storeEndDate(calendar,context) else endDate
    }

    fun getEndDate(context: Context) = PrefUtils[context,PREFKEY_ENDDATE,0]

    private fun parseCalendar(calendar: Calendar):Int{
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DATE)

        val currentDate = year *1000 + month * 100 + day

        return currentDate
    }

    private fun storeEndDate(calendar: Calendar,context: Context):Int{
        when(PrefUtils[context,context.resources.getString(SettingType.RESET.prefKeyResource),ResetType.MONTH.key]){
            ResetType.DAY.key -> calendar.add(Calendar.DATE,1)
            ResetType.WEEK.key -> calendar.add(Calendar.DATE,7)
            ResetType.MONTH.key -> calendar.add(Calendar.MONTH,1)
        }
        PrefUtils.put(context,PREFKEY_ENDDATE,parseCalendar(calendar))
        return parseCalendar(calendar)
    }

    private fun queryArrayDate(){

    }

}