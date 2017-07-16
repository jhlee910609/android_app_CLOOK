package com.example.junhee.weatherparse.domain.dailyWeather;

/**
 * Created by JunHee on 2017. 7. 11..
 */

public class WeatherData {

    private Response response;

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
    {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+"]";
    }
}



