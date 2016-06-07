package com.wacode.yuki.lowaccuracymappingsecond.UI.Map

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import butterknife.bindView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.wacode.yuki.lowaccuracymappingsecond.Entity.LocationEntity
import com.wacode.yuki.lowaccuracymappingsecond.Enum.SettingType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.AccuracyType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.FrequencyType
import com.wacode.yuki.lowaccuracymappingsecond.Enum.series.ResetType
import com.wacode.yuki.lowaccuracymappingsecond.R
import com.wacode.yuki.lowaccuracymappingsecond.UI.Map.HistoryDialog.HistoryDialogLayout
import com.wacode.yuki.lowaccuracymappingsecond.UI.Map.HistoryDialog.IMapping
import com.wacode.yuki.lowaccuracymappingsecond.UI.Setting.SettingActivity
import com.wacode.yuki.lowaccuracymappingsecond.Utils.CalendarUtils
import io.realm.RealmResults
import wacode.yuki.lowaccuracymapping.Enum.ColorType
import wacode.yuki.lowaccuracymapping.Service.MappingService
import wacode.yuki.lowaccuracymapping.Utils.RealmUtils
import wacode.yuki.newontapusha.Utils.PrefUtils

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,IMapping{

    private var mMap: GoogleMap? = null

    private val dialog:HistoryDialogLayout by bindView(R.id.dialog)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        setToolbar()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        if(isFirstStart()) InitPref()
        if(PrefUtils[this,SERVICESTATE,true]){
            startService(Intent(this,MappingService::class.java))
            PrefUtils.put(this,SERVICESTATE,false)
        }
    }

    override fun onResume() {
        super.onResume()
        if(mMap != null){
            getCurrent()
            loadArray()
        }
    }

    override fun onPause() {
        super.onPause()
        mMap!!.clear()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getCurrent()
        loadArray()
    }
    private fun getCurrent(){
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val lastLocate = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        putMapping(lastLocate.latitude,lastLocate.longitude)

        if (lastLocate != null) {
            val position = LatLng(lastLocate.latitude, lastLocate.longitude);
            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15F));
        } else {
            Toast.makeText(this, "現在地を取得出来ませんでした。", Toast.LENGTH_SHORT).show();}
    }

    private fun putMapping(latitude:Double,longitude:Double){
        val circleOptions = CircleOptions()
                .center( LatLng(latitude, longitude))
                .strokeWidth(1F)
                .strokeColor(PrefUtils[this,resources.getString(SettingType.COLOR.prefKeyResource),ColorType.BLUE.color])
                .radius(PrefUtils[this,resources.getString(SettingType.ACCURACY.prefKeyResource),AccuracyType.NORMAL.area].toDouble())
                .fillColor(PrefUtils[this,resources.getString(SettingType.COLOR.prefKeyResource),ColorType.BLUE.color])
        mMap!!.addCircle(circleOptions);
    }

    private fun setToolbar(){
        val toolBar = findViewById(R.id.toolBar) as Toolbar
        setSupportActionBar(toolBar)
        toolBar.title = resources.getString(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0,MENU_SETTING,0,resources.getString(R.string.menu_setting))
        menu.add(0,MENU_DIALOG,0,resources.getString(R.string.menu_histoy))
        return true
    }

    private fun checkResetSetting() = PrefUtils[this,resources.getString(SettingType.RESET.prefKeyResource),1]

    private fun loadArray(){
        mMap!!.clear()
        when(checkResetSetting()){
            ResetType.DAY.key -> parseRealmResult(RealmUtils.loadLocation(this,CalendarUtils.getCurrentDate()))
            ResetType.WEEK.key -> parseRealmResult(RealmUtils.loadLocation(this,CalendarUtils.getCurrentDate(),CalendarUtils.getEndDayForWeek()))
            ResetType.MONTH.key -> parseRealmResult(RealmUtils.loadLocation(this,CalendarUtils.getCurrentDate(),CalendarUtils.getEndDayForMonth()))
        }
    }

    private fun parseRealmResult(realmResults: RealmResults<LocationEntity>){
        mMap!!.clear()
        for(locationEntity in realmResults){
            putMapping(locationEntity.latitude,locationEntity.longitude)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            MENU_SETTING -> intentProfile()
            MENU_DIALOG -> showDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun intentProfile(){
        val intent = Intent(this,SettingActivity::class.java)
        startActivity(intent)
    }

    private fun showDialog(){
        dialog.visibility = View.VISIBLE

    }

    override fun setNormalMapping() {
        loadArray()
    }

    override fun setExtraMapping(startDate: Int, endDate: Int) {
        parseRealmResult(RealmUtils.loadLocation(this,startDate,endDate))
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
        private val MENU_SETTING = 0
        private val MENU_DIALOG = 1
        private val PREFKEY_INIT = "prefKey_init"
        val SERVICESTATE ="service_state"
    }

}
