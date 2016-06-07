package wacode.yuki.lowaccuracymapping.Service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.media.RingtoneManager
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.wacode.yuki.lowaccuracymappingsecond.R
import com.wacode.yuki.lowaccuracymappingsecond.UI.Map.MapsActivity
import com.wacode.yuki.lowaccuracymappingsecond.Utils.CalendarUtils
import wacode.yuki.lowaccuracymapping.Utils.RealmUtils
import wacode.yuki.newontapusha.Utils.PrefUtils
import java.util.*

/**
 * Created by yuki on 2016/05/18.
 */
class MappingService() : Service() {
    private val timer:Timer

    init {
        timer = Timer()
    }


    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"start")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"onStartCommand")

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val locationManager =getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val lastLocate:Location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                RealmUtils.storeCurrentLocation(this@MappingService,lastLocate.latitude,lastLocate.longitude,CalendarUtils.getCurrentDate())
                Log.d("saru","mapping")
            }

        },0,INTERVAL_PRERIOD.toLong())//TODO 時間をセットする

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        PrefUtils.put(this,MapsActivity.SERVICESTATE,true)
        sendNotification()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun sendNotification(){
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this,MapsActivity::class.java)

        val contentIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT)

        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(this)
        builder.mContentTitle = resources.getString(R.string.app_name)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.mStyle = NotificationCompat.BigTextStyle().bigText(resources.getString(R.string.notification_text))
        builder.mContentText = resources.getString(R.string.notification_text)
        builder.setWhen(System.currentTimeMillis())
        builder.setSound(uri)

        builder.setAutoCancel(true)

        builder.setContentIntent(contentIntent)
        notificationManager.notify(1,builder.build())
    }

    companion object{
        private val TAG = "MappingService"
        private val INTERVAL_PRERIOD = 300000
    }
}