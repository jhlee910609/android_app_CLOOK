package com.example.junhee.weatherparse.domain.skDust;

/**
 * Created by JunHee on 2017. 7. 16..
 */

public class Station {

    private String id;

    private String name;

    private String longitude;

    private String latitude;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", name = "+name+", longitude = "+longitude+", latitude = "+latitude+"]";
    }
}
