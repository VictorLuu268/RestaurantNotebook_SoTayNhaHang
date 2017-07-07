package com.victor.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Victor on 05/07/2017.
 */

public class Restaurant implements Serializable{
    private int id;
    private String name;
    // fix Bitmap not Serializable
    private transient Bitmap picture;
    private double longitude;
    private double latitude;

    public Restaurant() {
    }

    public Restaurant(int id, String name, Bitmap picture, double longitude, double latitude) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

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

    @Override
    public String toString() {
        return this.name;
    }
}
