package com.example.junhee.weatherparse.domain;

/**
 * Created by JunHee on 2017. 7. 14..
 */

public class UserInfo {

    private String selectedGender = "";
    private String selectedAge = "";
    private double currentLat = 0;
    private double currentLon = 0;

    private static UserInfo instance = null;

    public static UserInfo getInstance() {
        if (instance == null) {
            instance = new UserInfo();
            return instance;
        }
        return instance;
    }

    public String getSelectedGender() {
        return selectedGender;
    }

    public String getSelectedAge() {
        return selectedAge;
    }

    public void setSelectedGender(String selectedGender) {
        this.selectedGender = selectedGender;
    }

    public void setSelectedAge(String selectedAge) {
        this.selectedAge = selectedAge;
    }

    public double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(double currentLat) {
        this.currentLat = currentLat;
    }

    public double getCurrentLon() {
        return currentLon;
    }

    public void setCurrentLon(double currentLon) {
        this.currentLon = currentLon;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "selectedGender='" + selectedGender + '\'' +
                ", selectedAge='" + selectedAge + '\'' +
                ", currentLat=" + currentLat +
                ", currentLon=" + currentLon +
                '}';
    }
}
