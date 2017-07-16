package com.example.junhee.weatherparse.util.weatherParser;


import com.example.junhee.weatherparse.domain.dailyWeather.WeatherData;
import com.example.junhee.weatherparse.domain.discomfortData.DiscomfortData;
import com.example.junhee.weatherparse.domain.radiData.RadiDate;
import com.example.junhee.weatherparse.domain.skCurrentWeather.CurrentWeather;
import com.example.junhee.weatherparse.domain.skDust.DustData;
import com.example.junhee.weatherparse.domain.skTenDaysData.SkTenDaysWeather;
import com.google.gson.Gson;

/**
 * Created by JunHee on 2017. 7. 11..
 */

public class ConverJson {

    public static Gson gson = new Gson();

    public static WeatherData JsonToWeatherData(String jsonString) {
        WeatherData weatherData = gson.fromJson(jsonString, com.example.junhee.weatherparse.domain.dailyWeather.WeatherData.class);
        return weatherData;
    }

    public static DiscomfortData JsonToDiscomfortDate(String jsonString){
        return gson.fromJson(jsonString, DiscomfortData.class);
    }

    public static RadiDate JsonToRadiDate(String jsonString){
        return gson.fromJson(jsonString, RadiDate.class);
    }

    public static CurrentWeather JsonToSkCurrent(String jsonString){
        return gson.fromJson(jsonString, CurrentWeather.class);
    }

    public static SkTenDaysWeather JsonTo6Days(String jsonString){
        return gson.fromJson(jsonString, SkTenDaysWeather.class);
    }

    public static DustData JsonToDust(String jsonString){
        return gson.fromJson(jsonString, DustData.class);
    }
}
