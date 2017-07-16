package com.example.junhee.weatherparse.domain.skTenDaysData;

/**
 * Created by JunHee on 2017. 7. 14..
 */

public class Location {

    private String name;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+"]";
    }
}
