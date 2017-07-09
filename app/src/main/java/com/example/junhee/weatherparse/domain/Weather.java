package com.example.junhee.weatherparse.domain;

/**
 * Created by JunHee on 2017. 7. 9..
 */

public class Weather {

    private Hourly[] hourly;

    public Hourly[] getHourly ()
    {
        return hourly;
    }

    public void setHourly (Hourly[] hourly)
    {
        this.hourly = hourly;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [hourly = "+hourly+"]";
    }
}
