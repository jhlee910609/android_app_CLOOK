package com.example.junhee.weatherparse.domain.skDust;

/**
 * Created by JunHee on 2017. 7. 16..
 */

public class Pm10 {
    private String value;

    private String grade;

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getGrade ()
    {
        return grade;
    }

    public void setGrade (String grade)
    {
        this.grade = grade;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [value = "+value+", grade = "+grade+"]";
    }
}
