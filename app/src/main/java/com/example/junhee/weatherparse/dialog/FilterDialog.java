package com.example.junhee.weatherparse.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.junhee.weatherparse.R;
import com.example.junhee.weatherparse.adapter.FilterAdapter;

/**
 * Created by JunHee on 2017. 7. 18..
 */

public class FilterDialog extends Dialog {

    public Context mContext;
    Spinner spinnerLoc, spinnerAct;
    ImageView btnClose, btnSearch;
    CharSequence[] location = {"놀이공원", "바다", "한강", "시내", "게곡/산"};
    CharSequence[] activity = {"데이트", "피크닉", "여행", "휴식"};

    public FilterDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
        setWindowManager();
        setOnClick();
        setSpinner();
    }

    private void initWidget() {
        spinnerAct = (Spinner) findViewById(R.id.spinner_activity);
        spinnerLoc = (Spinner) findViewById(R.id.spinner_location);
        btnClose = (ImageView) findViewById(R.id.img_close);
        btnSearch = (ImageView) findViewById(R.id.img_search);
        btnClose.setClickable(true);
        btnSearch.setClickable(true);
    }

    public void setSpinner() {
        spinnerLoc.setAdapter(new FilterAdapter(mContext, R.layout.spinner_list, location));
        spinnerAct.setAdapter(new FilterAdapter(mContext, R.layout.spinner_list, activity));
        spinnerAct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 로직 작성
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerLoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 로직 작성
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setWindowManager() {
        WindowManager.LayoutParams window = new WindowManager.LayoutParams();
        window.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.dimAmount = 0.5f;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(window);
    }

    private void setOnClick() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(v.getContext(), "검색 중..", Toast.LENGTH_SHORT).show();
                    btnSearch.setImageResource(R.mipmap.icon_btn_search_hober);
                }

                if (event.getAction() == MotionEvent.ACTION_UP)
                    btnSearch.setImageResource(R.mipmap.icon_btn_search);


                return false;
            }
        });
    }
}
