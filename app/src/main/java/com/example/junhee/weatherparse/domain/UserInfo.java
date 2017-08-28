package com.example.junhee.weatherparse.domain;

/**
 * UserInfo의 경우, 1개의 객체만 존재하므로 싱글톤 패턴을 적용해보았다.
 */

public class UserInfo {

    private String selectedGender = "";
    private String selectedAge = "";

    // 최초 앱 실행 시, 날짜 및 지역 셋팅을 위해 미리 설정 값을 넣어놓았다.
    private String currentCity = "서울특별시";
    private String currentLat = "37.566481";
    private String currentLon = "126.977925";

    private String currentX = "60";
    private String currentY = "127";


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

    public void setCurrentLatLon(String currentLat, String currentLon) {
        this.currentLat = currentLat;
        this.currentLon = currentLon;
    }

    public String getCurrentLon() {
        return currentLon;
    }

    public String getCurrentX() {
        return currentX;
    }

    public void setCurrentXY(String currentX, String currentY) {
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public String getCurrentY() {
        return currentY;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
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
