package com.example.junhee.weatherparse.util;

import com.example.junhee.weatherparse.R;

/**
 * 날씨 관련 상수값 및 날씨 상황에 따른 이미지 처리를 위해 해당 클래스를 만들었다. 
 */

public class Const {

    public static class AuthorKey {
        public static final String KMA_KEY = "";
        public static final String SK_KEY = "";

    }

    public static class WeatherType {
        public static final String RAIN_PROB = "POP";
        public static final String RAIN_TYPE = "PTY";
        public static final String RAIN_6HR = "R06";
        public static final String HUMID = "REH";
        public static final String SNOW_6HR = "S06";
        public static final String SKY_STATUS = "SKY";
        public static final String TEMP_6HR = "T3H";
        public static final String TEMP_MIN = "TMN";
        public static final String TEMP_MAX = "TMX";
        public static final String WIND_EW = "UUU";
        public static final String WIND_SN = "VVV";
        public static final String WAVE = "WAV";
        public static final String WIND_DIR = "VEC";
        public static final String WIND_SPEED = "WSD";
    }

    public static class ForecstTime {
        public static final String FCST_01 = "0900";
        public static final String FCST_02 = "1200";
        public static final String FCST_03 = "1500";
        public static final String FCST_04 = "1800";
        public static final String FCST_05 = "2100";
        public static final String FCST_06 = "0000";
    }

    public static class SpecialChar {
        public static final String CELSIUS = "\u2103";
    }

    public static class WeatherValue {
        public static final String SKY_STATUS_SUNNY = "1";
        public static final String SKY_STATUS_LITTLE_CLOUDY = "2";
        public static final String SKY_STATUS_CLOUDY = "3";
        public static final String SKY_STATUS_FOGGY = "4";
        public static final String PTY_STATUS_NONE = "0";
        public static final String PTY_STATUS_RAINNY = "1";
        public static final String PTY_STATUS_RAINNY_SNOWY = "2";
        public static final String PTY_STATUS_SNOWY = "3";
    }

    public static class ImgResIconID {
        public static final int SKY_STATUS_SUNNY = R.mipmap.icon_sunny;
        public static final int SKY_STATUS_LITTLE_CLOUDY = R.mipmap.icon_cloudy;
        public static final int SKY_STATUS_CLOUDY = R.mipmap.icon_cloudy;
        public static final int SKY_STATUS_FOGGY = R.mipmap.icon_foggy;
        public static final int PTY_STATUS_NONE = 0;
        public static final int PTY_STATUS_THUNDER = R.mipmap.icon_thunder;

        public static final int PTY_STATUS_RAINNY = R.mipmap.icon_rainy;
        public static final int PTY_STATUS_RAINNY_SNOWY = R.mipmap.icon_rainy;
        public static final int PTY_STATUS_SNOWY = R.mipmap.icon_snowy;
        public static final int IMG_MALE = R.drawable.img_main_male;
        public static final int IMG_FEMALE = R.drawable.img_main_female;
    }
}
