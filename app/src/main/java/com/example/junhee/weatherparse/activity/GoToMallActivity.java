package com.example.junhee.weatherparse.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.junhee.weatherparse.R;
import com.example.junhee.weatherparse.domain.UserInfo;
import com.tsengvn.typekit.TypekitContextWrapper;

public class GoToMallActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView bannerOne, bannerTwo, bannerThree, bannerFour, goToMall_bottom, goToMall_top, goToMall_shoes, imgBack, bannerFive;
    TextView bottom, top, shoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_mall_activityu);

        initWidget();
        initImg();
        initOnClick();
    }

    private void initWidget() {
        bannerOne = (ImageView) findViewById(R.id.goToMall_banner_1);
        bannerTwo = (ImageView) findViewById(R.id.goToMall_banner_2);
        bannerThree = (ImageView) findViewById(R.id.goToMall_banner_3);
        bannerFour = (ImageView) findViewById(R.id.goToMall_banner_4);
        bannerFive = (ImageView) findViewById(R.id.goToMall_banner_5);

        goToMall_bottom = (ImageView) findViewById(R.id.goToMall_bottom_img);
        goToMall_top = (ImageView) findViewById(R.id.goToMall_Top_img);
        goToMall_shoes = (ImageView) findViewById(R.id.goToMall_shoes_img);

        bottom = (TextView) findViewById(R.id.goToMall_bottom_txt);
        top = (TextView) findViewById(R.id.goToMall_top_txt);
        shoes = (TextView) findViewById(R.id.goToMall_shoes_txt);

        imgBack = (ImageView) findViewById(R.id.goToMall_back);
    }

    private void setFemaleImg() {
        setGlide(R.drawable.style_buy_wm_sun_1, goToMall_bottom);
        setGlide(R.drawable.style_buy_wm_sun_2, goToMall_top);
        setGlide(R.drawable.style_buy_wm_sun_3, goToMall_shoes);
        setGlide(R.drawable.banner_mixxo, bannerOne);
        setGlide(R.drawable.banner_hnm, bannerTwo);
        setGlide(R.drawable.banner_8sc, bannerThree);
        setGlide(R.drawable.banner_zara_wm, bannerFour);
        setGlide(R.drawable.banner_topshop, bannerFive);
        bottom.setText("슬림핏 슬랙스");
        top.setText("린넨 슬릿 라운드");
        shoes.setText("Cross Detail 샌들");
    }

    private void setMaleImg() {
        setGlide(R.drawable.style_buy_m_sun_1, goToMall_bottom);
        setGlide(R.drawable.style_buy_m_sun_2, goToMall_top);
        setGlide(R.drawable.style_buy_m_sun_3, goToMall_shoes);
        setGlide(R.drawable.banner_uniqlo, bannerThree);
        setGlide(R.drawable.banner_topten, bannerTwo);
        setGlide(R.drawable.banner_8sc, bannerOne);
        setGlide(R.drawable.banner_zara_m, bannerFour);
        setGlide(R.drawable.banner_hconnect, bannerFive);
        bottom.setText("밴딩 크롭 슬랙스");
        top.setText("린넨 베이직 셔츠");
        shoes.setText("메달리온 슬");
    }

    private void initImg() {
        if (UserInfo.getInstance().getSelectedGender() == "female") {
            setFemaleImg();
        } else {
            setMaleImg();
        }
    }

    private void setGlide(int drawable, ImageView view) {
        Glide.with(getBaseContext())
                .load(drawable)
                .into(view);
    }

    private void initOnClick() {
        imgBack.setClickable(true);
        bannerOne.setClickable(true);
        bannerTwo.setClickable(true);
        bannerThree.setClickable(true);
        bannerFour.setClickable(true);

        bannerOne.setOnClickListener(this);
        bannerTwo.setOnClickListener(this);
        bannerThree.setOnClickListener(this);
        bannerFour.setOnClickListener(this);
    }

    private void goToShop(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View v) {
        if (UserInfo.getInstance().getSelectedGender() == "female") {
            switch (v.getId()) {
                case R.id.goToMall_banner_1:
                    goToShop(v.getContext(), "https://goo.gl/dpp9Bn");
                    break;

                case R.id.goToMall_banner_2:
                    goToShop(v.getContext(), "https://goo.gl/W1yRsd");
                    break;

                case R.id.goToMall_banner_3:
                    goToShop(v.getContext(), "https://goo.gl/lBYoGV");
                    break;

                case R.id.goToMall_banner_4:
                    goToShop(v.getContext(), "https://goo.gl/dfbc2m");
                    break;

                case R.id.goToMall_banner_5:
                    goToShop(v.getContext(), "http://www.topman.com/?geoip=home");

                case R.id.goToMall_back:
                    finish();
                    break;
            }
        } else {
            switch (v.getId()) {
                case R.id.goToMall_banner_1:
                    goToShop(v.getContext(), "https://goo.gl/Xv4idR");
                    break;

                case R.id.goToMall_banner_2:
                    goToShop(v.getContext(), "http://www.topten10.co.kr/main/main.asp");
                    break;

                case R.id.goToMall_banner_3:
                    goToShop(v.getContext(), "https://goo.gl/lBYoGV");
                    break;

                case R.id.goToMall_banner_4:
                    goToShop(v.getContext(), "https://goo.gl/Qyi9y8");
                    break;

                case R.id.goToMall_banner_5:
                    goToShop(v.getContext(), "https://goo.gl/Qyi9y8");

                case R.id.goToMall_back:
                    finish();
                    break;
            }
        }
    }
}
