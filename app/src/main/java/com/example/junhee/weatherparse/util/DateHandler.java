package com.example.junhee.weatherparse.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 기상청 API와 SK Planet Weather API의 날씨 정보 GET 시, 요구하는 날짜 형식이 달라 Handler를 클래스에 모아 만들었다.
 */

public class DateHandler {

    public static long now = System.currentTimeMillis();

    public static String changeYyyyMMddHH() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        return sdf.format(new Date(now));
    }

    public static String changerYyyyMMdd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date(now));
    }

    public static String changeKK(){
        SimpleDateFormat sdf = new SimpleDateFormat("kk");
        return sdf.format(new Date(now));
    }

    public static String changerHHmm() {

        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        return sdf.format(new Date(now));
    }

    public static String changeHH() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        return sdf.format(new Date(now));
    }

    public static String getWeekName() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("en", "US"));
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime()).toUpperCase();
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", new Locale("en", "US"));
        return sdf.format(new Date(now));

    }
}
