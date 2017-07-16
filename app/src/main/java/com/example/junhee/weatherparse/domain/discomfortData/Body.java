package com.example.junhee.weatherparse.domain.discomfortData;


/**
 * Created by JunHee on 2017. 7. 13..
 */

public class Body {

    private IndexModel indexModel;

    public IndexModel getIndexModel ()
    {
        return indexModel;
    }

    public void setIndexModel (IndexModel indexModel)
    {
        this.indexModel = indexModel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [indexModel = "+indexModel+"]";
    }
}
