package com.example.junhee.weatherparse.domain;

/**
 * Created by JunHee on 2017. 7. 9..
 */

public class Precipitation {

    private String sinceOntime;

    private String type;

    public String getSinceOntime ()
    {
        return sinceOntime;
    }

    public void setSinceOntime (String sinceOntime)
    {
        this.sinceOntime = sinceOntime;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sinceOntime = "+sinceOntime+", type = "+type+"]";
    }
}
