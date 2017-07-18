package com.example.junhee.weatherparse.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.junhee.weatherparse.R;
import com.example.junhee.weatherparse.domain.GeoInfo;
import com.example.junhee.weatherparse.domain.Weather3hr;
import com.example.junhee.weatherparse.domain.dailyWeather.Body;
import com.example.junhee.weatherparse.domain.dailyWeather.Item;
import com.example.junhee.weatherparse.domain.dailyWeather.Items;
import com.example.junhee.weatherparse.domain.dailyWeather.Response;
import com.example.junhee.weatherparse.domain.dailyWeather.WeatherData;
import com.example.junhee.weatherparse.util.Const;
import com.example.junhee.weatherparse.util.DateHandler;
import com.example.junhee.weatherparse.util.weatherParser.ConverJson;
import com.example.junhee.weatherparse.util.weatherParser.Remote;
import com.example.junhee.weatherparse.util.weatherParser.TaskInterface;

import static com.example.junhee.weatherparse.util.weatherParser.WeatherParser.setKmaUri;


/**
 * 1. data setting
 * 2. activity
 * 3.
 */

public class MainWeatherFrag extends Fragment implements TaskInterface, CustomDialog.CustomDiaListener {

    View view = null;
    ImageView imgSky1, imgSky2, imgSky3, imgSky4, imgSky5, imgSky6;
    TextView temp1, temp2, temp3, temp4, temp5, temp6;
    TextView time1, time2, time3, time4, time5, time6;
    String fcstFromCurrent = "";
    private String x = "61";
    private String y = "125";

    // TODO 바꿈 ======================
    public static String baseDate = DateHandler.changerYyyyMMdd();
    Weather3hr weather3hr = Weather3hr.getInstance();

    public MainWeatherFrag() {
        // Required empty public constructor
    }

    public String getX() {
        return x;
    }

    public void setXY(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getY() {
        return y;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main_weather, container, false);
            initWidget();
            getDataFromNet();
        }
        return view;
    }

    /**
     * Request Url setting
     * REST 통신 시도
     */

    public void getDataFromNet() {
        // TODO =========== 바꿔야함!!!!!!!!!!!!!!!!!!!!!!!!!!!
        fcstFromCurrent = setKmaUri(baseDate, x, y);
        Log.e("MainWeatherFrag", "fcstFromCurrent : " + fcstFromCurrent);
        Log.e("MainWeatherFrag", " x : " + x + ", y : " + y);
        Remote.newTask(fcstFromCurrent, this);
    }

    private void initWidget() {

        imgSky1 = (ImageView) view.findViewById(R.id.img_main_time_sky1);
        imgSky2 = (ImageView) view.findViewById(R.id.img_main_time_sky2);
        imgSky3 = (ImageView) view.findViewById(R.id.img_main_time_sky3);
        imgSky4 = (ImageView) view.findViewById(R.id.img_main_time_sky4);
        imgSky5 = (ImageView) view.findViewById(R.id.img_main_time_sky5);
        imgSky6 = (ImageView) view.findViewById(R.id.img_main_time_sky6);

        temp1 = (TextView) view.findViewById(R.id.txt_main_time_temp1);
        temp2 = (TextView) view.findViewById(R.id.txt_main_time_temp2);
        temp3 = (TextView) view.findViewById(R.id.txt_main_time_temp3);
        temp4 = (TextView) view.findViewById(R.id.txt_main_time_temp4);
        temp5 = (TextView) view.findViewById(R.id.txt_main_time_temp5);
        temp6 = (TextView) view.findViewById(R.id.txt_main_time_temp6);

        time1 = (TextView) view.findViewById(R.id.txt_main_time1);
        time2 = (TextView) view.findViewById(R.id.txt_main_time2);
        time3 = (TextView) view.findViewById(R.id.txt_main_time3);
        time4 = (TextView) view.findViewById(R.id.txt_main_time4);
        time5 = (TextView) view.findViewById(R.id.txt_main_time5);
        time6 = (TextView) view.findViewById(R.id.txt_main_time6);
    }

    public void setMainUi() {
        // TODO 반복되는 코드가 있기 때문에 정리하기! .. 쌍으로 묶어서 정리할 수 있을 것 같다.
        setEachUi(Const.ForecstTime.FCST_01, Const.WeatherType.TEMP_6HR, Const.WeatherType.SKY_STATUS, temp1, imgSky1);
        setEachUi(Const.ForecstTime.FCST_02, Const.WeatherType.TEMP_6HR, Const.WeatherType.SKY_STATUS, temp2, imgSky2);
        setEachUi(Const.ForecstTime.FCST_03, Const.WeatherType.TEMP_6HR, Const.WeatherType.SKY_STATUS, temp3, imgSky3);
        setEachUi(Const.ForecstTime.FCST_04, Const.WeatherType.TEMP_6HR, Const.WeatherType.SKY_STATUS, temp4, imgSky4);
        setEachUi(Const.ForecstTime.FCST_05, Const.WeatherType.TEMP_6HR, Const.WeatherType.SKY_STATUS, temp5, imgSky5);
        setEachUi(Const.ForecstTime.FCST_06, Const.WeatherType.TEMP_6HR, Const.WeatherType.SKY_STATUS, temp6, imgSky6);
    }

    private void setEachUi(String fcstTime, String category1, String category2, TextView temp, ImageView view) {
        temp.setText(weather3hr.getFcstValue(fcstTime, category1) + Const.SpecialChar.CELSIUS);
        setSkyImage(fcstTime, category2, view);
    }

    private void setSkyImage(String fcstTime, String category, ImageView img) {
        // 1. 고려사항이 SKY 상황
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
        Glide.with(getContext())
                .load(resId)
                .into(img);
    }

    /**
     * @param jsonResult : 통신 후, Response 받은 jsonString
     * @param url : 분기를 위해 Request url도 받아옴
     * 데이터를 받아 온 후, Widget setting까지 완료
     */
    @Override
    public void exectue(String jsonResult, String url) {
        if (url.equals(fcstFromCurrent)) {
            WeatherData weatherData = ConverJson.JsonToWeatherData(jsonResult);
            Response response = weatherData.getResponse();
            Body body = response.getBody();
            Items items = body.getItems();
            Item[] item = items.getItem();
            Log.e("MainWeatherFrag", "=== fcstFromCurrent : " + url.toString());
            // TODO 받아온 데이터 셋팅 전, hashMap<>(); 비우기
            weather3hr.weathers3hr.clear();
            weather3hr.setDatasFromKma(item, baseDate, Const.ForecstTime.FCST_01);
            weather3hr.setDatasFromKma(item, baseDate, Const.ForecstTime.FCST_02);
            weather3hr.setDatasFromKma(item, baseDate, Const.ForecstTime.FCST_03);
            weather3hr.setDatasFromKma(item, baseDate, Const.ForecstTime.FCST_04);
            weather3hr.setDatasFromKma(item, baseDate, Const.ForecstTime.FCST_05);
            weather3hr.setDatasFromKma(item, (Integer.parseInt(baseDate) + 1) + "", Const.ForecstTime.FCST_06);
        }
        Log.e("MainWeatherFrag", "=================== execute();");
        setMainUi();
    }

    /**
     * @param geoInfo : CustomDialog를 통해 받아온 GeoInfo(지역에 따른 좌표 정보를 담은 클래스)객체를 넘겨 받음
     * 받아온 geoInfo 좌표를 기반으로 다시 REST 통신 시도
     */

    @Override
    public void shareValue(GeoInfo geoInfo) {
        x = geoInfo.getX();
        y = geoInfo.getY();
        Log.e("MainWeatherFrag", geoInfo.toString());
        getDataFromNet();
    }
}


