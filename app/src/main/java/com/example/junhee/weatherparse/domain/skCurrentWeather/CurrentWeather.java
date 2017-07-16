package com.example.junhee.weatherparse.domain.skCurrentWeather;

/**
 * Created by JunHee on 2017. 7. 13..
 */

public class CurrentWeather {

    private Result result;

    private Common common;

    private Weather weather;

    public Result getResult ()
    {
        return result;
    }

    public void setResult (Result result)
    {
        this.result = result;
    }

    public Common getCommon ()
    {
        return common;
    }

    public void setCommon (Common common)
    {
        this.common = common;
    }

    public Weather getWeather ()
    {
        return weather;
    }

    public void setWeather (Weather weather)
    {
        this.weather = weather;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", common = "+common+", weather = "+weather+"]";
    }
}
