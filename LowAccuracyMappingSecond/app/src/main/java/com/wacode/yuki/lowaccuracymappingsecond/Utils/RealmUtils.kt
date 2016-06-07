package wacode.yuki.lowaccuracymapping.Utils

import android.content.Context
import com.wacode.yuki.lowaccuracymappingsecond.Entity.LocationEntity
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults

/**
 * Created by yuki on 2016/05/18.
 */
object RealmUtils {

    private val dbName = "LowAccuracy.realm"
    private val date ="date"

    private fun getRealm(context: Context):Realm{
        val config = RealmConfiguration.Builder(context).build()
        return Realm.getInstance(config)
    }


    fun storeCurrentLocation(context: Context,latitude:Double,longitude:Double,date:Int){
        val realm = getRealm(context)
        realm.beginTransaction()

        val locationEntity  = realm.createObject(LocationEntity::class.java)
        locationEntity.latitude = latitude
        locationEntity.longitude = longitude
        locationEntity.date = date

        realm.commitTransaction()
    }

    fun loadLocation(context: Context,today:Int):RealmResults<LocationEntity>{
        val realm = getRealm(context)
        val realmResult = realm!!.where(LocationEntity::class.java)
                                .equalTo(date,today)
                                .findAll()
        return realmResult
    }

    fun loadLocation(context: Context,startDay:Int,endDay:Int):RealmResults<LocationEntity>{
        val realm = getRealm(context)
        val realmResult = realm!!.where(LocationEntity::class.java)
                                .between(date,startDay,endDay)
                                .findAll()
        return realmResult
    }

    fun loadLocation(context: Context):RealmResults<LocationEntity>{
        val realm = getRealm(context)
        return realm!!.where(LocationEntity::class.java).findAll()
    }




}
