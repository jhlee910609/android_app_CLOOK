package com.example.junhee.weatherparse.domain.radiData;

/**
 * Created by JunHee on 2017. 7. 13..
 */

public class IndexModel {

    private String areaNo;

    private String tomorrow;

    private String theDayAfterTomorrow;

    private String today;

    private String code;

    private String date;

    public String getAreaNo ()
    {
        return areaNo;
    }

    public void setAreaNo (String areaNo)
    {
        this.areaNo = areaNo;
    }

    public String getTomorrow ()
    {
        return tomorrow;
    }

    public void setTomorrow (String tomorrow)
    {
        this.tomorrow = tomorrow;
    }

    public String getTheDayAfterTomorrow ()
    {
        return theDayAfterTomorrow;
    }

    public void setTheDayAfterTomorrow (String theDayAfterTomorrow)
    {
        this.theDayAfterTomorrow = theDayAfterTomorrow;
    }

    public String getToday ()
    {
        return today;
    }

    public void setToday (String today)
    {
        this.today = today;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [areaNo = "+areaNo+", tomorrow = "+tomorrow+", theDayAfterTomorrow = "+theDayAfterTomorrow+", today = "+today+", code = "+code+", date = "+date+"]";
    }
}
