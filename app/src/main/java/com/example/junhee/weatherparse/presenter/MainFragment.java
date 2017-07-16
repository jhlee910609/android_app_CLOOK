package com.example.junhee.weatherparse.presenter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.junhee.weatherparse.R;
import com.example.junhee.weatherparse.activity.DetailWeatherAcitivity;
import com.example.junhee.weatherparse.domain.GeoInfo;
import com.example.junhee.weatherparse.domain.UserInfo;
import com.example.junhee.weatherparse.domain.WeatherLife;
import com.example.junhee.weatherparse.domain.radiData.IndexModel;
import com.example.junhee.weatherparse.domain.radiData.RadiDate;
import com.example.junhee.weatherparse.domain.skCurrentWeather.CurrentWeather;
import com.example.junhee.weatherparse.domain.skCurrentWeather.Hourly;
import com.example.junhee.weatherparse.domain.skCurrentWeather.Sky;
import com.example.junhee.weatherparse.domain.skCurrentWeather.Temperature;
import com.example.junhee.weatherparse.domain.skCurrentWeather.Weather;
import com.example.junhee.weatherparse.util.Const;
import com.example.junhee.weatherparse.util.DateHandler;
import com.example.junhee.weatherparse.util.weatherParser.ConverJson;
import com.example.junhee.weatherparse.util.weatherParser.Remote;
import com.example.junhee.weatherparse.util.weatherParser.TaskInterface;

import static com.example.junhee.weatherparse.util.weatherParser.WeatherParser.setRadiUri;
import static com.example.junhee.weatherparse.util.weatherParser.WeatherParser.setSkCurrentUri;

public class MainFragment extends Fragment implements TaskInterface, CustomDialog.CustomDiaListner {

    private String selectedAge = "";
    private String selectedGender = "";

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    public MainWeatherFrag mainWeatherFrag = new MainWeatherFrag();
    TextView tempMax, tempMin, radi, week, tempCurrent, time;
    public ImageView mainImg, mainWeatherImg;
    private CustomDialog mCustomDialog;

    View view = null;
    FrameLayout mainFrame = null;
    TextView selectCity;

    Temperature temperature = null;
    Sky sky = null;

    String currentSkUrl = "";
    String currentRadiUrl = "";

    // 신사역 기준이다... 지금은 ㅇㅇㅇ
    public String currentLat = 37.498214 + "";
    public String currentLon = 130.027610 + "";

    private UserInfo userInfo = UserInfo.getInstance();

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main_fragment, container, false);
            initWidget();
            setOnClick();
            setFrameOnClick();
            setImg();
        }
        setFragment();
        getDataFromNet();
        Log.e("MainFragment", "========= currentLat : " + currentLat + "======= currentLon : " + currentLon);

        return view;
    }

    private void getDataFromNet() {
        currentSkUrl = setSkCurrentUri(currentLat, currentLon);
        Log.e("Mainfragment", "============== currentSkUrl : " + currentSkUrl);
        currentRadiUrl = setRadiUri(DateHandler.changeYyyyMMddHH());
        Log.e("Mainfragment", "============== currecurrentRadiUr : " + currentRadiUrl);
        Remote.newTask(currentRadiUrl, this);
        Remote.newTask(currentSkUrl, this);
    }

    private void setOnClick() {

        selectCity.setClickable(true);
        selectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLocDialog();
            }
        });
    }

    private void showLocDialog() {

        mCustomDialog = new CustomDialog(getActivity(), this);
        Log.e("MainFragment", "========== mCustomDialog");
        mCustomDialog.setContentView(R.layout.custom_dialog_layout);
        mCustomDialog.show();
    }


    private void initWidget() {

        selectCity = (TextView) view.findViewById(R.id.main_cityName);
        mainFrame = (FrameLayout) view.findViewById(R.id.main_frameLayout);
        tempMin = (TextView) view.findViewById(R.id.main_currentMinTemp);
        tempMax = (TextView) view.findViewById(R.id.main_currentMaxTemp);
        tempCurrent = (TextView) view.findViewById(R.id.main_currentTemp);
        radi = (TextView) view.findViewById(R.id.main_txt_radi);
        week = (TextView) view.findViewById(R.id.main_week);
        time = (TextView) view.findViewById(R.id.main_CurrentTime);
        mainImg = (ImageView) view.findViewById(R.id.main_illust);
        mainWeatherImg = (ImageView) view.findViewById(R.id.main_img_skyStatus);
//        updateMainUi();
    }

    private void setFragment() {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_frameLayout, mainWeatherFrag);
        transaction.commit();
    }

    private void setFrameOnClick() {
        mainFrame.setClickable(true);
        mainFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailWeatherAcitivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    private void updateMainUi() {
        tempMax.setText(temperature.getTmax() + Const.SpecialChar.CELSIUS);
        tempMin.setText(temperature.getTmin() + Const.SpecialChar.CELSIUS);
        Log.e("MainFragment", temperature.toString());
        tempCurrent.setText(temperature.getTc() + Const.SpecialChar.CELSIUS);
        week.setText(DateHandler.getWeekName());
        time.setText(DateHandler.getCurrentTime());
    }

    public void setImg() {
        if (userInfo.getSelectedGender() == "male") {
            Glide.with(getContext())
                    .load(Const.ImgResIconID.IMG_MALE)
                    .into(mainImg);
        } else if (userInfo.getSelectedGender() == "female") {
            Glide.with(getContext())
                    .load(Const.ImgResIconID.IMG_FEMALE)
                    .into(mainImg);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void exectue(String jsonResult, String url) {

        if (url.equals(currentSkUrl)) {

            CurrentWeather fcstcastFromcurrent = ConverJson.JsonToSkCurrent(jsonResult);
            Weather weather = fcstcastFromcurrent.getWeather();
            Hourly[] hourlies = weather.getHourly();
            temperature = hourlies[0].getTemperature();
            sky = hourlies[0].getSky();
            setHourlyImgFromCode(sky.getCode(), mainWeatherImg);
            Log.e("Mainfragment", "temperature : " + temperature);
            updateMainUi();

        } else if (url.equals(currentRadiUrl)) {

            RadiDate radiDate = ConverJson.JsonToRadiDate(jsonResult);
            if (radiDate != null && !"".equals(radiDate)) {
                com.example.junhee.weatherparse.domain.radiData.Response response = radiDate.getResponse();
                com.example.junhee.weatherparse.domain.radiData.Body body = response.getBody();
                IndexModel indexModel = body.getIndexModel();
                String currentRadi = indexModel.getToday();

                if ("".equals(currentRadi) || currentRadi == null) {
                    radi.setText("-");

                } else {
                    WeatherLife weatherLife = WeatherLife.getInstance();
                    weatherLife.setCurrentRaidInt(weatherLife.matchRadiToStr(currentRadi));
                    weatherLife.setCurrentRadiStr(currentRadi);
                    radi.setText(weatherLife.matchRadiToStr(currentRadi));
                }
            }
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

        Glide.with(getActivity().getBaseContext())
                .load(resId)
                .into(img);
    }

    @Override
    public void shareValue(GeoInfo geoInfo) {
        currentLat = geoInfo.getLat();
        currentLon = geoInfo.getLon();
        if(mainWeatherFrag != null) {

            mainWeatherFrag.setX(geoInfo.getX());
            mainWeatherFrag.setY(geoInfo.getY());
            Log.e("MainFragment", geoInfo.toString());
        }

        getDataFromNet();
        selectCity.setText(geoInfo.getCityName());
    }
}
