package com.example.junhee.weatherparse.domain.dailyWeather;

/**
 * Created by JunHee on 2017. 7. 11..
 */
public class Items
{
    private Item[] item;

    public Item[] getItem ()
    {
        return item;
    }

    public void setItem (Item[] item)
    {
        this.item = item;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [item = "+item+"]";
    }
}