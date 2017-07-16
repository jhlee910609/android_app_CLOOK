package com.example.junhee.weatherparse.presenter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.junhee.weatherparse.R;
import com.example.junhee.weatherparse.activity.GoToMallActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FashionDetailFragment extends Fragment {

    private ImageView fashionImg, imgFilter;
    private TextView showDetail;
    private View view = null;
    private ScrollView scrollView;

    public FashionDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        initWidget();
        initImg();
        setOnClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_fashion_detail, container, false);
        }
        return view;
    }

    private void initWidget() {
        fashionImg = (ImageView) view.findViewById(R.id.fashionDetail_img);
        imgFilter = (ImageView) view.findViewById(R.id.imgFilter);
        showDetail = (TextView) view.findViewById(R.id.txt_fashionDetail_show);
        scrollView = (ScrollView) view.findViewById(R.id.fashion_detail_scroll);
    }

    private void initImg(){
        Glide.with(getContext())
                .load(R.mipmap.img_wm_sun)
                .into(fashionImg);
    }



    private void setOnClick(){
        imgFilter.setClickable(true);
        imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "click filter", Toast.LENGTH_SHORT).show();
            }
        });

        showDetail.setClickable(true);
        showDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GoToMallActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

}
