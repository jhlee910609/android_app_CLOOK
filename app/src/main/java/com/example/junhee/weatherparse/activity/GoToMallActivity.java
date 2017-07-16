package com.example.junhee.weatherparse.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.junhee.weatherparse.R;
import com.tsengvn.typekit.TypekitContextWrapper;

public class GoToMallActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView bannerOne, bannerTwo, bannerThree, bannerFour, goToMall_bottom, goToMall_top, goToMall_shoes, imgBack;

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

        goToMall_bottom = (ImageView) findViewById(R.id.goToMall_bottom_img);
        goToMall_top = (ImageView) findViewById(R.id.goToMall_Top_img);
        goToMall_shoes = (ImageView) findViewById(R.id.goToMall_shoes_img);

        imgBack = (ImageView) findViewById(R.id.goToMall_back);
    }

    private void initImg() {
        setGlide(R.drawable.style_buy_wm_sun_1, goToMall_bottom);
        setGlide(R.drawable.style_buy_wm_sun_2, goToMall_top);
        setGlide(R.drawable.style_buy_wm_sun_3, goToMall_shoes);
        setGlide(R.drawable.banner_mixxo, bannerOne);
        setGlide(R.drawable.banner_hnm, bannerTwo);
        setGlide(R.drawable.banner_8sc, bannerThree);
        setGlide(R.drawable.banner_zara_m, bannerFour);
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

    private void goToShop(Context context, String url){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goToMall_banner_1:
                goToShop(v.getContext(), "https://goo.gl/dpp9Bn");
                break;

            case R.id.goToMall_banner_2:
                break;

            case R.id.goToMall_banner_3:
                break;

            case R.id.goToMall_banner_4:
                break;

            case R.id.goToMall_back:
                finish();
                break;
        }
    }
}
