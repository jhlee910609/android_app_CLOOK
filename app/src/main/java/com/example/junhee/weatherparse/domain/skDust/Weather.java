package com.example.junhee.weatherparse.domain.skDust;

/**
 * Created by JunHee on 2017. 7. 16..
 */

public class Weather {

    private Dust[] dust;

    public Dust[] getDust ()
    {
        return dust;
    }

    public void setDust (Dust[] dust)
    {
        this.dust = dust;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dust = "+dust+"]";
    }
}
