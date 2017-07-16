package com.example.junhee.weatherparse.domain.skCurrentWeather;

/**
 * Created by JunHee on 2017. 7. 13..
 */

public class Grid {

    private String village;

    private String county;

    private String longitude;

    private String latitude;

    private String city;

    public String getVillage ()
    {
        return village;
    }

    public void setVillage (String village)
    {
        this.village = village;
    }

    public String getCounty ()
    {
        return county;
    }

    public void setCounty (String county)
    {
        this.county = county;
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

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [village = "+village+", county = "+county+", longitude = "+longitude+", latitude = "+latitude+", city = "+city+"]";
    }
}
