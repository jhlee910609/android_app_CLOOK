package com.example.junhee.weatherparse.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.junhee.weatherparse.R;
import com.example.junhee.weatherparse.domain.GeoInfo;
import com.example.junhee.weatherparse.domain.Weather3hr;
import com.example.junhee.weatherparse.domain.discomfortData.Body;
import com.example.junhee.weatherparse.domain.discomfortData.DiscomfortData;
import com.example.junhee.weatherparse.domain.discomfortData.IndexModel;
import com.example.junhee.weatherparse.domain.discomfortData.Response;
import com.example.junhee.weatherparse.domain.skCurrentWeather.CurrentWeather;
import com.example.junhee.weatherparse.domain.skCurrentWeather.Hourly;
import com.example.junhee.weatherparse.domain.skCurrentWeather.Wind;
import com.example.junhee.weatherparse.domain.skDust.Dust;
import com.example.junhee.weatherparse.domain.skDust.DustData;
import com.example.junhee.weatherparse.domain.skDust.Pm10;
import com.example.junhee.weatherparse.domain.skTenDaysData.Forecast6days;
import com.example.junhee.weatherparse.domain.skTenDaysData.SkTenDaysWeather;
import com.example.junhee.weatherparse.domain.skTenDaysData.Sky;
import com.example.junhee.weatherparse.domain.skTenDaysData.Temperature;
import com.example.junhee.weatherparse.domain.skTenDaysData.Weather;
import com.example.junhee.weatherparse.presenter.CustomDialog;
import com.example.junhee.weatherparse.util.Const;
import com.example.junhee.weatherparse.util.DateHandler;
import com.example.junhee.weatherparse.util.weatherParser.ConverJson;
import com.example.junhee.weatherparse.util.weatherParser.Remote;
import com.example.junhee.weatherparse.util.weatherParser.TaskInterface;
import com.example.junhee.weatherparse.util.weatherParser.WeatherParser;
import com.tsengvn.typekit.TypekitContextWrapper;

import static java.lang.Integer.parseInt;

public class DetailWeatherAcitivity extends AppCompatActivity implements View.OnClickListener, TaskInterface, CustomDialog.CustomDiaListner {

    // =========================== [ 메인 날씨 위젯 ] =============================
    TextView detailCurrentTime, detailWeekName, detailCityName, detailCurrentTemp;
    ImageView detailCurrentImg;
    ProgressBar progressBar;


    // =========================== [시간대별 날씨 위젯] =============================
    String uri = "";
    ImageView time_img1, time_img2, time_img3, time_img4, time_img5, time_img6;
    TextView time_time_txt1, time_time_txt2, time_time_txt3, time_time_txt4, time_time_txt5, time_time_txt6;
    TextView time_temp_txt1, time_temp_txt2, time_temp_txt3, time_temp_txt4, time_temp_txt5, time_temp_txt6;
    TextView time_humid_txt1, time_humid_txt2, time_humid_txt3, time_humid_txt4, time_humid_txt5, time_humid_txt6;

    // =========================== [상세 날씨 위젯] =============================
    TextView sensTempDegree, sensTempTxt;
    TextView windPower, windDir, windSpeed;
    TextView dustDegree, dustTxt;
    ImageView btnBack;

    // =======================[ 주간 날씨 위젯 ]=======================
    TextView tempMin1, tempMin2, tempMin3, tempMin4, tempMin5, tempMin6, tempMin7;
    TextView tempMax1, tempMax2, tempMax3, tempMax4, tempMax5, tempMax6, tempMax7;
    ImageView weekImg1, weekImg2, weekImg3, weekImg4, weekImg5, weekImg6, weekImg7;

    // ============== [ 필요한 데이터들 ] ==================
    SkTenDaysWeather tendays = null;
    Weather3hr weather3hr = Weather3hr.getInstance();

    String discomfortUrl = "";
    String tenDaysUrl = "";
    String dustUrl = "";
    String currentWeather = "";

    // 신사역 x,y 좌표
    String x = 60 + "";
    String y = 127 + "";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_weather_acitivity);

        initWidget();
        getDataFromNet();
        updateUi();
        setProgressBar();

        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }

    private void getDataFromNet() {

        tenDaysUrl = WeatherParser.setSk6daysUri(x, y);
        discomfortUrl = WeatherParser.setDiscomfortUri(DateHandler.changeYyyyMMddHH());
        dustUrl = WeatherParser.setSkDustUrl(x, y);
        currentWeather = WeatherParser.setSkCurrentUri(x, y);

        Remote.newTask(tenDaysUrl, this);
        Remote.newTask(discomfortUrl, this);
        Remote.newTask(dustUrl, this);
        Remote.newTask(currentWeather, this);

    }

    private void initWidget() {

        time_img1 = (ImageView) findViewById(R.id.img_time1);
        time_img2 = (ImageView) findViewById(R.id.img_time2);
        time_img3 = (ImageView) findViewById(R.id.img_time3);
        time_img4 = (ImageView) findViewById(R.id.img_time4);
        time_img5 = (ImageView) findViewById(R.id.img_time5);
        time_img6 = (ImageView) findViewById(R.id.img_time6);

        time_temp_txt1 = (TextView) findViewById(R.id.temp_time1);
        time_temp_txt2 = (TextView) findViewById(R.id.temp_time2);
        time_temp_txt3 = (TextView) findViewById(R.id.temp_time3);
        time_temp_txt4 = (TextView) findViewById(R.id.temp_time4);
        time_temp_txt5 = (TextView) findViewById(R.id.temp_time5);
        time_temp_txt6 = (TextView) findViewById(R.id.temp_time6);

        time_humid_txt1 = (TextView) findViewById(R.id.humid_time1);
        time_humid_txt2 = (TextView) findViewById(R.id.humid_time2);
        time_humid_txt3 = (TextView) findViewById(R.id.humid_time3);
        time_humid_txt4 = (TextView) findViewById(R.id.humid_time4);
        time_humid_txt5 = (TextView) findViewById(R.id.humid_time5);
        time_humid_txt6 = (TextView) findViewById(R.id.humid_time6);

        sensTempDegree = (TextView) findViewById(R.id.txt_detail_temp);
        sensTempTxt = (TextView) findViewById(R.id.txt_detail_temp_kr);

        windDir = (TextView) findViewById(R.id.txt_detail_wind_dir);
        windPower = (TextView) findViewById(R.id.txt_detail_wind_power);
        windSpeed = (TextView) findViewById(R.id.txt_detail_wind_speed);

        tempMin1 = (TextView) findViewById(R.id.tempMin_week1);
        tempMin2 = (TextView) findViewById(R.id.tempMin_week2);
        tempMin3 = (TextView) findViewById(R.id.tempMin_week3);
        tempMin4 = (TextView) findViewById(R.id.tempMin_week4);
        tempMin5 = (TextView) findViewById(R.id.tempMin_week5);
        tempMin6 = (TextView) findViewById(R.id.tempMin_week6);
        tempMin7 = (TextView) findViewById(R.id.tempMax_week7);

        tempMax1 = (TextView) findViewById(R.id.tempMax_week1);
        tempMax2 = (TextView) findViewById(R.id.tempMax_week2);
        tempMax3 = (TextView) findViewById(R.id.tempMax_week3);
        tempMax4 = (TextView) findViewById(R.id.tempMax_week4);
        tempMax5 = (TextView) findViewById(R.id.tempMax_week5);
        tempMax6 = (TextView) findViewById(R.id.tempMax_week6);
        tempMax7 = (TextView) findViewById(R.id.tempMax_week7);

        weekImg1 = (ImageView) findViewById(R.id.img_week1);
        weekImg2 = (ImageView) findViewById(R.id.img_week2);
        weekImg3 = (ImageView) findViewById(R.id.img_week3);
        weekImg4 = (ImageView) findViewById(R.id.img_week4);
        weekImg5 = (ImageView) findViewById(R.id.img_week5);
        weekImg6 = (ImageView) findViewById(R.id.img_week6);
        weekImg7 = (ImageView) findViewById(R.id.img_week7);


        time_img1 = (ImageView) findViewById(R.id.img_time1);
        time_img2 = (ImageView) findViewById(R.id.img_time2);
        time_img3 = (ImageView) findViewById(R.id.img_time3);
        time_img4 = (ImageView) findViewById(R.id.img_time4);
        time_img5 = (ImageView) findViewById(R.id.img_time5);
        time_img6 = (ImageView) findViewById(R.id.img_time6);

        detailCurrentTime = (TextView) findViewById(R.id.detail_time);
        detailWeekName = (TextView) findViewById(R.id.detail_week);
        detailCurrentImg = (ImageView) findViewById(R.id.detail_img_weather);
        detailCityName = (TextView) findViewById(R.id.detail_city);
        detailCurrentTemp = (TextView) findViewById(R.id.detail_currentTemp);

        dustDegree = (TextView) findViewById(R.id.txt_detail_dust);
        dustTxt = (TextView) findViewById(R.id.txt_detail_dust_kr);

        progressBar = (ProgressBar) findViewById(R.id.moonProgress);
    }

    private void updateUi() {

        // 1. 시간별 예보 셋
        setWeatherOnTime(time_temp_txt1, time_humid_txt1, time_img1, Const.ForecstTime.FCST_01);
        setWeatherOnTime(time_temp_txt2, time_humid_txt2, time_img2, Const.ForecstTime.FCST_02);
        setWeatherOnTime(time_temp_txt3, time_humid_txt3, time_img3, Const.ForecstTime.FCST_03);
        setWeatherOnTime(time_temp_txt4, time_humid_txt4, time_img4, Const.ForecstTime.FCST_04);
        setWeatherOnTime(time_temp_txt5, time_humid_txt5, time_img5, Const.ForecstTime.FCST_05);
        setWeatherOnTime(time_temp_txt6, time_humid_txt6, time_img6, Const.ForecstTime.FCST_06);

        // 2. 주간 날씨 셋
//        setWeatherOnweek();
    }

    // ======= [ 시간별 예보 셋] ========
    private void setWeatherOnTime(TextView tv1, TextView tv2, ImageView img, String fcstTime) {

        tv1.setText(weather3hr.getFcstValue(fcstTime, Const.WeatherType.TEMP_6HR) + Const.SpecialChar.CELSIUS);
        tv2.setText(weather3hr.getFcstValue(fcstTime, Const.WeatherType.HUMID));

        int resId = 0;
        switch (weather3hr.getFcstValue(Const.ForecstTime.FCST_01, Const.WeatherType.SKY_STATUS)) {
            case "1":
                resId = Const.ImgResIconID.SKY_STATUS_SUNNY;
                break;

            case "2":
                resId = Const.ImgResIconID.SKY_STATUS_LITTLE_CLOUDY;
                break;

            case "3":
                resId = Const.ImgResIconID.SKY_STATUS_CLOUDY;
                break;

            case "4":
                resId = Const.ImgResIconID.SKY_STATUS_FOGGY;
                break;
        }
        Glide.with(getBaseContext())
                .load(resId)
                .into(img);
    }

    private void setWeatherOnweek(com.example.junhee.weatherparse.domain.skTenDaysData.Temperature temp, Sky sky) {

        tempMax1.setText(temp.getTmax3day() + Const.SpecialChar.CELSIUS);
        tempMin1.setText(temp.getTmin3day() + Const.SpecialChar.CELSIUS);
        setSkSkyImageFromCode(sky.getAmCode3day(), weekImg1);

        tempMax2.setText(temp.getTmax4day() + Const.SpecialChar.CELSIUS);
        tempMin2.setText(temp.getTmin4day() + Const.SpecialChar.CELSIUS);
        setSkSkyImageFromCode(sky.getAmCode4day(), weekImg2);


        tempMax3.setText(temp.getTmax5day() + Const.SpecialChar.CELSIUS);
        tempMin3.setText(temp.getTmin5day() + Const.SpecialChar.CELSIUS);
        setSkSkyImageFromCode(sky.getAmCode5day(), weekImg3);


        tempMax4.setText(temp.getTmax6day() + Const.SpecialChar.CELSIUS);
        tempMin4.setText(temp.getTmin6day() + Const.SpecialChar.CELSIUS);
        setSkSkyImageFromCode(sky.getAmCode6day(), weekImg4);


        tempMax5.setText(temp.getTmax7day() + Const.SpecialChar.CELSIUS);
        tempMin5.setText(temp.getTmin7day() + Const.SpecialChar.CELSIUS);
        setSkSkyImageFromCode(sky.getAmCode7day(), weekImg5);


        tempMax6.setText(temp.getTmax8day() + Const.SpecialChar.CELSIUS);
        tempMin6.setText(temp.getTmin8day() + Const.SpecialChar.CELSIUS);
        setSkSkyImageFromCode(sky.getAmCode8day(), weekImg6);

        tempMax7.setText(temp.getTmax9day() + Const.SpecialChar.CELSIUS);
        tempMin7.setText(temp.getTmin9day() + Const.SpecialChar.CELSIUS);
        setSkSkyImageFromCode(sky.getAmCode9day(), weekImg7);
    }

    private void setSkSkyImageFromCode(String skyCode, ImageView img) {

        int resId = 0;
        switch (skyCode) {
            case "SKY_W01":
                resId = Const.ImgResIconID.SKY_STATUS_SUNNY;
                break;

            case "SKY_W02":
                resId = Const.ImgResIconID.SKY_STATUS_LITTLE_CLOUDY;
                break;

            case "SKY_W03":
                resId = Const.ImgResIconID.SKY_STATUS_CLOUDY;
                break;

            case "SKY_W04":
                resId = Const.ImgResIconID.SKY_STATUS_FOGGY;
                break;


            case "SKY_W07":
                resId = Const.ImgResIconID.PTY_STATUS_RAINNY;
                break;

            case "SKY_W09":
                resId = Const.ImgResIconID.PTY_STATUS_RAINNY;
                break;

            case "SKY_W10":
                resId = Const.ImgResIconID.PTY_STATUS_RAINNY;
                break;

            case "SKY_W11":
                resId = Const.ImgResIconID.PTY_STATUS_RAINNY_SNOWY;
                break;

            case "SKY_W12":
                resId = Const.ImgResIconID.PTY_STATUS_SNOWY;
                break;

            case "SKY_W13":
                resId = Const.ImgResIconID.PTY_STATUS_SNOWY;
                break;
        }

        Glide.with(getBaseContext())
                .load(resId)
                .into(img);
    }

    private void setTemp(String disomfortStr, String discomfortCode) {
        sensTempDegree.setText(discomfortCode);
        sensTempTxt.setText(disomfortStr);
    }

    private void setWind(Hourly hourly) {
        Wind wind = hourly.getWind();
        windSpeed.setText(wind.getWspd());
        windDir.setText(convertDirToStr(wind.getWdir()));
        windPower.setText(convertSpdToPw(wind.getWspd()));
    }

    private String convertSpdToPw(String speed) {
        double intSpeed = Double.parseDouble(speed);
        String result = "";

        if (intSpeed >= 14) {
            result = "매우 강함";
        } else if (intSpeed >= 9) {
            result = "강함";
        } else if (intSpeed >= 4) {
            result = "약간 강함";
        } else {
            result = "약함";
        }
        return result;
    }

    private String convertDirToStr(String windDir) {
        double degree = Double.parseDouble(windDir);
        String result = "";
        if (degree < 45) {
            result = "북-북동풍";
        } else if (degree >= 45 && degree < 90) {
            result = "북동-동풍";
        } else if (degree >= 90 && degree < 135) {
            result = "동풍-남동풍";
        } else if (degree >= 135 && degree < 180) {
            result = "남동-남풍";
        } else if (degree >= 180 && degree < 225) {
            result = "남-남서풍";
        } else if (degree >= 225 && degree < 270) {
            result = "남서-서풍";
        } else if (degree >= 270 && degree < 315) {
            result = "서-북서풍";
        } else if (degree >= 315 && degree <= 360) {
            result = "북서-북";
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }


    @Override
    public void exectue(String jsonResult, String url) {
        if (url.equals(tenDaysUrl)) {
            tendays = ConverJson.JsonTo6Days(jsonResult);
            Log.e("DetailWeather, execute() : ", tendays.toString());
            Weather weather = tendays.getWeather();
            Forecast6days[] forecast6dayses = weather.getForecast6days();
            Temperature temp = forecast6dayses[0].getTemperature();
            Sky sky = forecast6dayses[0].getSky();
            Log.e("DetailWeatherActivity", sky.toString());

            setWeatherOnweek(temp, sky);

        } else if (url.equals(discomfortUrl)) {

            DiscomfortData date = ConverJson.JsonToDiscomfortDate(jsonResult);
            Response response = date.getResponse();
            Body body = response.getBody();
            IndexModel indexModel = body.getIndexModel();
            String discomfort = indexModel.getH3();
            String discomfortStr = discomforDegreeToStr(discomfort);
            sensTempTxt.setText(discomfortStr);
            sensTempDegree.setText(discomfort);

        } else if (url.equals(dustUrl)) {

            DustData dustData = ConverJson.JsonToDust(jsonResult);
            com.example.junhee.weatherparse.domain.skDust.Weather weather = dustData.getWeather();
            Dust[] dust = weather.getDust();
            Pm10 pm10 = dust[0].getPm10();
            Log.e("DetailWeatherActivity", pm10.toString());
            dustDegree.setText(pm10.getValue());
            dustTxt.setText(pm10.getGrade());

        } else if (url.equals(currentWeather)) {

            CurrentWeather currentWeather = ConverJson.JsonToSkCurrent(jsonResult);
            com.example.junhee.weatherparse.domain.skCurrentWeather.Weather weather = currentWeather.getWeather();
            Hourly[] hourlies = weather.getHourly();
            com.example.junhee.weatherparse.domain.skCurrentWeather.Temperature temp = hourlies[0].getTemperature();
            com.example.junhee.weatherparse.domain.skCurrentWeather.Sky sky = hourlies[0].getSky();

            detailCurrentTemp.setText(temp.getTc() + Const.SpecialChar.CELSIUS);
            detailCurrentTime.setText(DateHandler.getCurrentTime());
            detailWeekName.setText(DateHandler.getWeekName());
            Log.e("DetailWeatherActivity", sky.getCode());

            setWind(hourlies[0]);
            setHourlyImgFromCode(sky.getCode(), detailCurrentImg);
        }
    }

    private void setHourlyImgFromCode(String skyCode, ImageView img) {

        int resId = 0;
        switch (skyCode) {
            case "SKY_O01":
                resId = Const.ImgResIconID.SKY_STATUS_SUNNY;
                break;

            case "SKY_O02":
                resId = Const.ImgResIconID.SKY_STATUS_LITTLE_CLOUDY;
                break;

            case "SKY_O03":
                resId = Const.ImgResIconID.SKY_STATUS_CLOUDY;
                break;

            case "SKY_O04":
                resId = Const.ImgResIconID.PTY_STATUS_RAINNY;
                break;

            case "SKY_O05":
                resId = Const.ImgResIconID.PTY_STATUS_SNOWY;
                break;

            case "SKY_O06":
                resId = Const.ImgResIconID.PTY_STATUS_RAINNY_SNOWY;
                break;

            case "SKY_O07":
                resId = Const.ImgResIconID.SKY_STATUS_FOGGY;
                break;

            case "SKY_O08":
                resId = Const.ImgResIconID.PTY_STATUS_RAINNY;
                break;

            case "SKY_O09":
                resId = Const.ImgResIconID.PTY_STATUS_SNOWY;
                break;

            case "SKY_O10":
                resId = Const.ImgResIconID.PTY_STATUS_RAINNY_SNOWY;
                break;

            case "SKY_011":
                resId = Const.ImgResIconID.PTY_STATUS_THUNDER;
                break;

            case "SKY_012":
                resId = Const.ImgResIconID.PTY_STATUS_THUNDER;
                break;

            case "SKY_W13":
                resId = Const.ImgResIconID.PTY_STATUS_RAINNY_SNOWY;
                break;
        }

        Glide.with(getBaseContext())
                .load(resId)
                .into(img);
    }


    private String discomforDegreeToStr(String discomfortCode) {
        int tempDis = parseInt(discomfortCode);
        String result = "";
        if (tempDis >= 80) {
            result = "매우높음";

        } else if (tempDis >= 75) {
            result = "높음";

        } else if (tempDis >= 68) {
            result = "보통";

        } else {
            result = "낮음";
        }
        return result;
    }

    private void setProgressBar() {
        String current = DateHandler.changeKK();
        final int intCurrent = Integer.parseInt(current);
        progressBar.getProgressDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        progressBar.setProgress(intCurrent);
    }

    @Override
    public void shareValue(GeoInfo geoInfo) {
        x = geoInfo.getX();
        y = geoInfo.getY();
        detailCityName.setText(geoInfo.getCityName());
    }
}