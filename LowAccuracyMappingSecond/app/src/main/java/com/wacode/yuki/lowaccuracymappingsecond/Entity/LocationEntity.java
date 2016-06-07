package com.wacode.yuki.lowaccuracymappingsecond.Entity;


import io.realm.RealmObject;

/**
 * Created by yuki on 2016/05/18.
 */
public class LocationEntity extends RealmObject {
    private double longitude;
    private double latitude;
    private int date;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
