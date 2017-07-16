package com.example.junhee.weatherparse.domain;

/**
 * Created by JunHee on 2017. 7. 14..
 */

public class WeatherLife {

    private static WeatherLife instance = null;
    private String currentRadiStr = "";
    private String currentRaidInt = "";
    private String currentSensTempStr = "";
    private String currentSensTempInt = "";
    private String currentDust = "";
    private String sunRiseTime = "";
    private String moonRiseTime = "";

    public static WeatherLife getInstance() {
        if (instance == null) {
            instance = new WeatherLife();
            return instance;
        }
        return instance;
    }

    private WeatherLife() {

    }

    public String getCurrentRadiStr() {
        return currentRadiStr;
    }

    public void setCurrentRadiStr(String currentRadiStr) {
        this.currentRadiStr = currentRadiStr;
    }

    public String getCurrentSensTempStr() {
        return currentSensTempStr;
    }

    public void setCurrentSensTempStr(String currentSensTempStr) {
        this.currentSensTempStr = currentSensTempStr;
    }

    public String getCurrentDust() {
        return currentDust;
    }

    public void setCurrentDust(String currentDust) {
        this.currentDust = currentDust;
    }

    public String getSunRiseTime() {
        return sunRiseTime;
    }

    public void setSunRiseTime(String sunRiseTime) {
        this.sunRiseTime = sunRiseTime;
    }

    public String getMoonRiseTime() {
        return moonRiseTime;
    }

    public void setMoonRiseTime(String moonRiseTime) {
        this.moonRiseTime = moonRiseTime;
    }

    public String getCurrentSensTempInt() {
        return currentSensTempInt;
    }

    public void setCurrentSensTempInt(String currentSensTempInt) {
        this.currentSensTempInt = currentSensTempInt;
    }

    public String getCurrentRaidInt() {
        return currentRaidInt;
    }

    public void setCurrentRaidInt(String currentRaidInt) {
        this.currentRaidInt = currentRaidInt;
    }

    public String matchRadiToStr(String currentRadi) {

        int intCurrentRadi = Integer.parseInt(currentRadi);

        if (intCurrentRadi >= 11) {
            currentRadiStr = "위험";
        } else if (intCurrentRadi >= 8) {
            currentRadiStr = "매우 높음";
        } else if (intCurrentRadi >= 6) {
            currentRadiStr = "높음";
        } else if (intCurrentRadi >= 3) {
            currentRadiStr = "보통";
        } else if (intCurrentRadi >= 0) {
            currentRadiStr = "낮음";
        }
        return currentRadiStr;
    }

    @Override
    public String toString() {
        return "WeatherLife{" +
                "currentRadiStr='" + currentRadiStr + '\'' +
                ", currentRaidInt='" + currentRaidInt + '\'' +
                ", currentSensTempStr='" + currentSensTempStr + '\'' +
                ", currentSensTempInt='" + currentSensTempInt + '\'' +
                ", currentDust='" + currentDust + '\'' +
                ", sunRiseTime='" + sunRiseTime + '\'' +
                ", moonRiseTime='" + moonRiseTime + '\'' +
                '}';
    }
}
