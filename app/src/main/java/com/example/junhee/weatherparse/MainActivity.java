package com.example.junhee.weatherparse;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.junhee.weatherparse.activity.IntroActivity;
import com.example.junhee.weatherparse.adapter.ViewPagerAdapter;
import com.example.junhee.weatherparse.domain.Weather3hr;
import com.example.junhee.weatherparse.fragments.FashionDetailFragment;
import com.example.junhee.weatherparse.fragments.MainFragment;
import com.example.junhee.weatherparse.util.system.BackCloseHandler;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;
import java.util.List;

import me.kaelaela.verticalviewpager.VerticalViewPager;

public class MainActivity extends AppCompatActivity {

    List<Fragment> fragments = new ArrayList<>();

    private ViewPagerAdapter adapter;
    private VerticalViewPager viewPager;
    public MainFragment mainFragment;
    private FashionDetailFragment fashionDetailFragment;
    private BackCloseHandler backCloseHandler;
    private String selectedAge = "";
    private String selectedGender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainFragment = new MainFragment();
        setContentView(R.layout.activity_main);
        setBackCloseHandler();
        setFragment();
        setViewPager();


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            selectedAge = IntroActivity.SELECTED_AGE;
            Log.e("Main", "=========== selectedAge : " + selectedAge);

            selectedGender = IntroActivity.SELECTED_GENDER;
            Log.e("Main", "=========== selectedGender : " + selectedGender);
        }
    }

    private void setBackCloseHandler() {
        backCloseHandler = new BackCloseHandler(this);
    }

    @Override
    public void onBackPressed() {
        backCloseHandler.onBackPressed();
    }

    public void setFragment() {
        fashionDetailFragment = new FashionDetailFragment();
        fragments.add(mainFragment);
        fragments.add(fashionDetailFragment);
    }

    private void setViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager = (VerticalViewPager) findViewById(R.id.main_viewpager);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}