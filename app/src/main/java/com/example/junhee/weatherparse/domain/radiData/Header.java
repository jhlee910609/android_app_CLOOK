package com.example.junhee.weatherparse.domain.radiData;

/**
 * Created by JunHee on 2017. 7. 13..
 */

public class Header {

    private String returnCode;

    private String successYN;

    private String errMsg;

    public String getReturnCode ()
    {
        return returnCode;
    }

    public void setReturnCode (String returnCode)
    {
        this.returnCode = returnCode;
    }

    public String getSuccessYN ()
    {
        return successYN;
    }

    public void setSuccessYN (String successYN)
    {
        this.successYN = successYN;
    }

    public String getErrMsg ()
    {
        return errMsg;
    }

    public void setErrMsg (String errMsg)
    {
        this.errMsg = errMsg;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [returnCode = "+returnCode+", successYN = "+successYN+", errMsg = "+errMsg+"]";
    }
}
