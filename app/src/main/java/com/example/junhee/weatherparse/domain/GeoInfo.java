package com.example.junhee.weatherparse.domain;

/**
 * Created by JunHee on 2017. 7. 16..
 */

public class GeoInfo {

    private String cityName = "";
    private String lat = "";
    private String lon = "";
    private String x = "";
    private String y = "";

    public GeoInfo(String cityName, String lat, String lon, String x, String y) {
        this.cityName = cityName;
        this.lat = lat;
        this.lon = lon;
        this.x = x;
        this.y = y;
    }

    public GeoInfo() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "GeoInfo{" +
                "cityName='" + cityName + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
