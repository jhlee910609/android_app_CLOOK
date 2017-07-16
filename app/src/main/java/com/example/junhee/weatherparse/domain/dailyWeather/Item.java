package com.example.junhee.weatherparse.domain.dailyWeather;

/**
 * Created by JunHee on 2017. 7. 11..
 */


public class Item {

    private String category;

    private String fcstDate;

    private String fcstTime;

    private String baseTime;

    private String baseDate;

    private String ny;

    private String nx;

    private String fcstValue;

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getFcstDate ()
    {
        return fcstDate;
    }

    public void setFcstDate (String fcstDate)
    {
        this.fcstDate = fcstDate;
    }

    public String getFcstTime ()
    {
        return fcstTime;
    }

    public void setFcstTime (String fcstTime)
    {
        this.fcstTime = fcstTime;
    }

    public String getBaseTime ()
    {
        return baseTime;
    }

    public void setBaseTime (String baseTime)
    {
        this.baseTime = baseTime;
    }

    public String getBaseDate ()
    {
        return baseDate;
    }

    public void setBaseDate (String baseDate)
    {
        this.baseDate = baseDate;
    }

    public String getNy ()
    {
        return ny;
    }

    public void setNy (String ny)
    {
        this.ny = ny;
    }

    public String getNx ()
    {
        return nx;
    }

    public void setNx (String nx)
    {
        this.nx = nx;
    }

    public String getFcstValue ()
    {
        return fcstValue;
    }

    public void setFcstValue (String fcstValue)
    {
        this.fcstValue = fcstValue;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [category = "+category+", fcstDate = "+fcstDate+", fcstTime = "+fcstTime+", baseTime = "+baseTime+", baseDate = "+baseDate+", ny = "+ny+", nx = "+nx+", fcstValue = "+fcstValue+"]";
    }

}