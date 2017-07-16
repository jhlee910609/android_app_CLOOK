package com.example.junhee.weatherparse.domain.discomfortData;

/**
 * Created by JunHee on 2017. 7. 13..
 */

public class DiscomfortData {

    private Response Response;

    public Response getResponse ()
    {
        return Response;
    }

    public void setResponse (Response Response)
    {
        this.Response = Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Response = "+Response+"]";
    }

}


