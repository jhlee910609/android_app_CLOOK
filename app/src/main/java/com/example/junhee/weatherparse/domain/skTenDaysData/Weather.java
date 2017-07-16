package com.example.junhee.weatherparse.domain.skTenDaysData;

/**
 * Created by JunHee on 2017. 7. 14..
 */

public class Weather {

    private Forecast6days[] forecast6days;

    public Forecast6days[] getForecast6days ()
    {
        return forecast6days;
    }

    public void setForecast6days (Forecast6days[] forecast6days)
    {
        this.forecast6days = forecast6days;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [forecast6days = "+forecast6days+"]";
    }
}
