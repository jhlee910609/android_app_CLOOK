package com.example.junhee.weatherparse.domain.radiData;

/**
 * Created by JunHee on 2017. 7. 13..
 */

public class Response {

    private Body body;

    private Header header;

    public Body getBody ()
    {
        return body;
    }

    public void setBody (Body body)
    {
        this.body = body;
    }

    public Header getHeader ()
    {
        return header;
    }

    public void setHeader (Header header)
    {
        this.header = header;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [body = "+body+", header = "+header+"]";
    }
}
