package com.example.junhee.weatherparse;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.junhee.weatherparse.domain.Data;
import com.example.junhee.weatherparse.domain.Hourly;
import com.example.junhee.weatherparse.domain.Weather;
import com.example.junhee.weatherparse.util.BackCloseHandler;
import com.example.junhee.weatherparse.util.MainChangeLoc;
import com.example.junhee.weatherparse.util.Remote;
import com.example.junhee.weatherparse.util.TaskInterface;
import com.google.gson.Gson;
import com.tsengvn.typekit.TypekitContextWrapper;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TaskInterface {

    public OnMainListener mListener;
    private BackCloseHandler backCloseHandler;

    FrameLayout mainFrame;
    TextView selectCity;

    String currentUri = "";
    public Weather weather = new Weather();
    final String URI_PREFIX = "http://apis.skplanetx.com/weather/current/hourly?";
    final String URI_MID = "version=";
    final String URI_LAT = "&lat=";
    final String URI_LON = "&lon=";
    final String URI_DEFAULT = "&city=&county=&village=";

    private String selectedAge = "";
    private String selectedGender = "";

    private MainWeatherFrag mainWeatherFrag = new MainWeatherFrag();
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWeatherUri(1, 37.328378, 127.087675);
        Remote.newTask(this);
        setFragment();
        setWidget();
        setOnClick();
        setFrameOnClick();
        setBackCloseHandler();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            selectedAge = IntroActivity.SELECTED_AGE;
            Log.e("Main", "=========== selectedAge : " + selectedAge);

            selectedGender = IntroActivity.SELECTED_GENDER;
            Log.e("Main", "=========== selectedGender : " + selectedGender);
        }
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }

    private void setBackCloseHandler(){
        backCloseHandler = new BackCloseHandler(this);
    }

    @Override
    public void onBackPressed() {
        backCloseHandler.onBackPressed();
    }

    private void setWidget(){
        selectCity = (TextView) findViewById(R.id.main_cityName);
        mainFrame = (FrameLayout) findViewById(R.id.main_frameLayout);
    }

    private void setOnClick(){
        selectCity.setClickable(true);
        selectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.add(R.id.main_popUp, new MainChangeLoc());
                transaction.commit();
            }
        });
    }

    public void setFragment() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_frameLayout, mainWeatherFrag);
        transaction.commit();
    }

    private void setFrameOnClick(){
        mainFrame.setClickable(true);
        mainFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailWeatherAcitivity.class);
                v.getContext().startActivity(intent);
                overridePendingTransition(R.anim.from_bottom_to_top, R.anim.from_top_to_bottom);
            }
        });


    }

    public void setWeatherUri(int version, double lat, double lon) {
        currentUri = URI_PREFIX + URI_MID + version + URI_LAT + lat + URI_LON + lon + URI_DEFAULT;
        Log.e("Main", "=========== currentUri : " + currentUri);
    }

    @Override
    public String getUrl() {
        return currentUri;
    }

    @Override
    public void postExecute(String jsonString) {
        Data data = convertJson(jsonString);
        Log.e("Main", "=========== data : " + data.toString());
        weather = data.getWeather();
        Hourly hourly[] = weather.getHourly();
        Log.e("Main", "========= hourly.length" + hourly.length);

        setWeatherInfo(hourly[0]);
    }

    private void setWeatherInfo(Hourly hourly) {
        setTemp(hourly);
        setWind(hourly);
        setHumid(hourly);
    }

    private void setHumid(Hourly hourly) {
        try {
            String str = "현재 습도 : " + hourly.getHumidity();
//            tv_humid.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTemp(Hourly hourly) {

        try {
            String str = "현재기온 = " + hourly.getTemperature().getTc() + "\n"
                    + "최고기온 = " + hourly.getTemperature().getTmax() + "\n"
                    + "최저기온 = " + hourly.getTemperature().getTmin();

            Log.e("Main", "Temp ====================" + hourly.getTemperature());
//            tv_temp.setText(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setWind(Hourly hourly) {

        try {
            String str = "풍향 = " + hourly.getWind().getWdir() + "\n풍속 = " + hourly.getWind().getWspd();
//            tv_wind.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Data convertJson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Data.class);
    }

    public interface OnMainListener {
        public void goData(Hourly hourly);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
