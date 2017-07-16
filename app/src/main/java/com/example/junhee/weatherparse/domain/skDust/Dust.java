package com.example.junhee.weatherparse.domain.skDust;

/**
 * Created by JunHee on 2017. 7. 16..
 */

public class Dust {

    private String timeObservation;

    private Station station;

    private Pm10 pm10;

    public String getTimeObservation ()
    {
        return timeObservation;
    }

    public void setTimeObservation (String timeObservation)
    {
        this.timeObservation = timeObservation;
    }

    public Station getStation ()
    {
        return station;
    }

    public void setStation (Station station)
    {
        this.station = station;
    }

    public Pm10 getPm10 ()
    {
        return pm10;
    }

    public void setPm10 (Pm10 pm10)
    {
        this.pm10 = pm10;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [timeObservation = "+timeObservation+", station = "+station+", pm10 = "+pm10+"]";
    }
}
