package com.example.junhee.weatherparse.domain;

/**
 * Created by JunHee on 2017. 7. 9..
 */

public class Hourly {

    private Wind wind;

    private String lightning;

    private String humidity;

    private Sky sky;

    private String timeRelease;

    private Precipitation precipitation;

    private Temperature temperature;

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getLightning() {
        return lightning;
    }

    public void setLightning(String lightning) {
        this.lightning = lightning;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Sky getSky() {
        return sky;
    }

    public void setSky(Sky sky) {
        this.sky = sky;
    }

    public String getTimeRelease() {
        return timeRelease;
    }

    public void setTimeRelease(String timeRelease) {
        this.timeRelease = timeRelease;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Precipitation precipitation) {
        this.precipitation = precipitation;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "ClassPojo [wind = " + wind + ", lightning = " + lightning + ", humidity = " + humidity + ", sky = " + sky + ", timeRelease = " + timeRelease + ", precipitation = " + precipitation + ", grid = " + ", temperature = " + temperature + "]";
    }
}
