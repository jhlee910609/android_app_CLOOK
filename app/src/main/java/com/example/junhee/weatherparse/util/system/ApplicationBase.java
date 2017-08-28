package com.example.junhee.weatherparse.util.system;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * 폰트 설정을 위해 Typekite 라이브러리를 사용하였다.
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
