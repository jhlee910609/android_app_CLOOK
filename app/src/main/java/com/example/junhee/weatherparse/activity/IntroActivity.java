package com.example.junhee.weatherparse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.junhee.weatherparse.R;
import com.example.junhee.weatherparse.domain.UserInfo;
import com.tsengvn.typekit.TypekitContextWrapper;

public class IntroActivity extends AppCompatActivity {

    Spinner spinner_gender, spinner_age;
    String selectedGender = "";
    String selectedAge = "";
    ImageView imgStart;
    TextView next;
    UserInfo userInfo = UserInfo.getInstance();


    public static String SELECTED_AGE = "selectAge";
    public static String SELECTED_GENDER = "selectGender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        setWidget();
        setOnSpinner();
        setImgStart();
        setTextOnClick();
    }

    private void setWidget() {
        imgStart = (ImageView) findViewById(R.id.img_start);
        spinner_age = (Spinner) findViewById(R.id.spinner_age);
        spinner_gender = (Spinner) findViewById(R.id.spinner_gender);
        next = (TextView) findViewById(R.id.txt_next);
    }

    private void setImgStart() {
        imgStart.setClickable(true);
        imgStart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    imgStart.setImageResource(R.mipmap.intro_btn_start_onclick);

                if (event.getAction() == MotionEvent.ACTION_UP)
                    imgStart.setImageResource(R.mipmap.intro_btn_start);

                return false;
            }
        });
        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(SELECTED_AGE, selectedAge);
                bundle.putString(SELECTED_GENDER, selectedGender);
                setOnUserInf(selectedGender, selectedAge);
                Log.e("Intro", UserInfo.getInstance().toString());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    private void setTextOnClick() {
        next.setClickable(true);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("IntroAct", UserInfo.getInstance().toString());
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    private void setOnSpinner() {
        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getSelectedItemPosition()) {
                    case 0:
                        selectedGender = "male";
                        Log.e("IntroActivity", "=================== gender : " + selectedGender);
                        break;

                    case 1:
                        selectedGender = "female";
                        Log.e("IntroActivity", "=================== gender : " + selectedGender);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getSelectedItemPosition()) {
                    case 0:
                        selectedAge = "twenties";
                        Log.e("IntroActivity", "=================== selectedAge : " + selectedAge);
                        break;

                    case 1:
                        selectedAge = "thirites";
                        Log.e("IntroActivity", "=================== selectedAge : " + selectedAge);
                        break;

                    case 2:
                        selectedAge = "fourties";
                        Log.e("IntroActivity", "=================== selectedAge : " + selectedAge);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    private void setOnUserInf(String selectedGender, String selectedAge) {
        userInfo.setSelectedGender(selectedGender);
        userInfo.setSelectedAge(selectedAge);
    }
}
