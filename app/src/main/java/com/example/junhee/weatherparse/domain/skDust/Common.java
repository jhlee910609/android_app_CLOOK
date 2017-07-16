package com.example.junhee.weatherparse.domain.skDust;

/**
 * Created by JunHee on 2017. 7. 16..
 */

public class Common {

    private String alertYn;

    private String stormYn;

    public String getAlertYn ()
    {
        return alertYn;
    }

    public void setAlertYn (String alertYn)
    {
        this.alertYn = alertYn;
    }

    public String getStormYn ()
    {
        return stormYn;
    }

    public void setStormYn (String stormYn)
    {
        this.stormYn = stormYn;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [alertYn = "+alertYn+", stormYn = "+stormYn+"]";
    }
}
