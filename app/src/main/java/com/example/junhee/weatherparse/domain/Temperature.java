package com.example.junhee.weatherparse.domain;

/**
 * Created by JunHee on 2017. 7. 9..
 */

public class Temperature {

    private String tmax;

    private String tc;

    private String tmin;

    public String getTmax ()
    {
        return tmax;
    }

    public void setTmax (String tmax)
    {
        this.tmax = tmax;
    }

    public String getTc ()
    {
        return tc;
    }

    public void setTc (String tc)
    {
        this.tc = tc;
    }

    public String getTmin ()
    {
        return tmin;
    }

    public void setTmin (String tmin)
    {
        this.tmin = tmin;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tmax = "+tmax+", tc = "+tc+", tmin = "+tmin+"]";
    }
}
