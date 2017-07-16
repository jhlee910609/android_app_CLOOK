package com.example.junhee.weatherparse.util.system;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by JunHee on 2017. 7. 10..
 */

public class ApplicationBase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "AppleSDGothicNeo_regular.ttf"))
                .addBold(Typekit.createFromAsset(this, "AppleSDGothicNeo_Bold.ttf"));
    }
}
