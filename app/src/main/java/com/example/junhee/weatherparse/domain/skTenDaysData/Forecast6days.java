package com.example.junhee.weatherparse.domain.skTenDaysData;
/**
 * Created by JunHee on 2017. 7. 14..
 */

public class Forecast6days {

    private Sky sky;

    private Location location;

    private String timeRelease;

    private Grid grid;

    private Temperature temperature;

    public Sky getSky ()
    {
        return sky;
    }

    public void setSky (Sky sky)
    {
        this.sky = sky;
    }

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public String getTimeRelease ()
    {
        return timeRelease;
    }

    public void setTimeRelease (String timeRelease)
    {
        this.timeRelease = timeRelease;
    }

    public Grid getGrid ()
    {
        return grid;
    }

    public void setGrid (Grid grid)
    {
        this.grid = grid;
    }

    public Temperature getTemperature ()
    {
        return temperature;
    }

    public void setTemperature (Temperature temperature)
    {
        this.temperature = temperature;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sky = "+sky+", location = "+location+", timeRelease = "+timeRelease+", grid = "+grid+", temperature = "+temperature+"]";
    }
}
