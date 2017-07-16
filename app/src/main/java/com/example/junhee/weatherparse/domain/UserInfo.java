package com.example.junhee.weatherparse.domain;

/**
 * Created by JunHee on 2017. 7. 14..
 */

public class UserInfo {

    private String selectedGender = "";
    private String selectedAge = "";

    private String currentLat = "";
    private String currentLon = "";

    private String currentX = "";
    private String currentY = "";

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

    public String getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(String currentLat) {
        this.currentLat = currentLat;
    }

    public String getCurrentLon() {
        return currentLon;
    }

    public void setCurrentLon(String currentLon) {
        this.currentLon = currentLon;
    }

    public String getCurrentX() {
        return currentX;
    }

    public void setCurrentX(String currentX) {
        this.currentX = currentX;
    }

    public String getCurrentY() {
        return currentY;
    }

    public void setCurrentY(String currentY) {
        this.currentY = currentY;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "selectedGender='" + selectedGender + '\'' +
                ", selectedAge='" + selectedAge + '\'' +
                ", currentLat='" + currentLat + '\'' +
                ", currentLon='" + currentLon + '\'' +
                ", currentX='" + currentX + '\'' +
                ", currentY='" + currentY + '\'' +
                '}';
    }
}
