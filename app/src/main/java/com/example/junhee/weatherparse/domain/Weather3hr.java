package com.example.junhee.weatherparse.domain;

import android.util.Log;

import com.example.junhee.weatherparse.domain.dailyWeather.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JunHee on 2017. 7. 12..
 */

public class Weather3hr {

    private static Weather3hr instance;

    public static Weather3hr getInstance() {
        if (instance == null) {
            Log.e("===", "create.........................");
            instance = new Weather3hr();
        }
        return instance;
    }

    private Weather3hr() {

    }

    public static Map<String, Map<String, Item>> weathers3hr = new HashMap<>();

    public static Map<String, Map<String, Item>> getWeathers3hr() {
        return weathers3hr;
    }

    public void setDatasFromKma(Item[] items, String fcstDate, String fcstTime) {
        Map<String, Item> categories = new HashMap<>();
        for (Item tempItem : items) {
            if (tempItem.getFcstDate().equals(fcstDate) && tempItem.getFcstTime().equals(fcstTime)) {
                categories.put(tempItem.getCategory(), tempItem);
                weathers3hr.put(tempItem.getFcstTime(), categories);
            }
        }
    }

    public String getFcstValue(String fcstTime, String category) {
        return weathers3hr.get(fcstTime).get(category).getFcstValue();
    }
}
