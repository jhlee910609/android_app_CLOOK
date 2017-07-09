package com.example.junhee.weatherparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.junhee.weatherparse.domain.Data;
import com.example.junhee.weatherparse.domain.Hourly;
import com.example.junhee.weatherparse.domain.Weather;
import com.example.junhee.weatherparse.util.Remote;
import com.example.junhee.weatherparse.util.TaskInterface;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TaskInterface {

    @BindView(R.id.tv_temp)
    TextView tv_temp;

    @BindView(R.id.tv_wind)
    TextView tv_wind;

    @BindView(R.id.tv_humid)
    TextView tv_humid;

    String currentUri = "";
    Weather weather = new Weather();
    final String URI_PREFIX = "http://apis.skplanetx.com/weather/current/hourly?";
    final String URI_MID = "version=";
    final String URI_LAT = "&lat=";
    final String URI_LON = "&lon=";
    final String URI_DEFAULT = "&city=&county=&village=";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setWeatherUri(1, 37.328378, 127.087675);
        Remote.newTask(this);
    }

    public void setWeatherUri(int version, double lat, double lon){
        currentUri = URI_PREFIX + URI_MID + version + URI_LAT + lat + URI_LON + lon + URI_DEFAULT;
        Log.e("Main", "=========== currentUri : " + currentUri);
    }

    @Override
    public String getUrl() {
        return currentUri;
    }

    @Override
    public void postExecute(String jsonString){
        Data data = convertJson(jsonString);
        Log.e("Main", "=========== data : " + data.toString());
        weather = data.getWeather();
        Hourly hourly[] = weather.getHourly();
        Log.e("Main", "========= hourly.length" + hourly.length);

        setWeatherInfo(hourly[0]);
    }

    private void setWeatherInfo(Hourly hourly){
        setTemp(hourly);
        setWind(hourly);
        setHumid(hourly);
    }

    private void setHumid(Hourly hourly){
        try {
            String str = "현재 습도 : " + hourly.getHumidity();
            tv_humid.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTemp(Hourly hourly){

        try {
            String str = "현재기온 = " + hourly.getTemperature().getTc() + "\n"
                    + "최고기온 = " + hourly.getTemperature().getTmax() + "\n"
                    + "최저기온 = " + hourly.getTemperature().getTmin();

            Log.e("Main", "Temp ====================" + hourly.getTemperature());
            tv_temp.setText(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setWind(Hourly hourly){

        try {
            String str = "풍향 = " + hourly.getWind().getWdir() + "\n풍속 = " + hourly.getWind().getWspd();
            tv_wind.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Data convertJson(String jsonString){
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Data.class);
    }
}
