package com.example.junhee.weatherparse.domain;

/**
 * Created by JunHee on 2017. 7. 9..
 */

public class Data {

    private Result result;

    private Weather weather;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "ClassPojo [result = " + result + ", weather = " + weather + "]";
    }
}

