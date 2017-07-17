package com.example.junhee.weatherparse.presenter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junhee.weatherparse.R;
import com.example.junhee.weatherparse.domain.GeoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunHee on 2017. 7. 15..
 */

public class CustomDialog extends Dialog {

    private EditText mEditText;
    private TextView mTitle;
    private ListView mListview;
    private Context context;
    private ImageView mBtnClose;
    private ImageView mBtnSearch;
    List<GeoInfo> cityList;
    private CustomDiaListener mListner;
    public int mPosition = 0;
    private String cityLat = "";
    private String cityLon = "";
    private ArrayAdapter<String> adapter;

    public String getCityLat() {
        return cityLat;
    }

    public void setCityLat(String cityLat) {
        this.cityLat = cityLat;
    }

    public String getCityLon() {
        return cityLon;
    }

    public void setCityLon(String cityLon) {
        this.cityLon = cityLon;
    }

    public CustomDialog(@NonNull Context context, CustomDiaListener callback) {
        super(context);
        this.context = context;
        this.mListner = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setWindowManager();
        initWidget();
        setData();
        setmListview();
        setBtnOnClick();
        setFilerOnEdit();
    }



    private void setMListner() {
        GeoInfo geoInfo = cityList.get(mPosition);
        mListner.shareValue(geoInfo);
    }

    public GeoInfo getGeoInfo(){
        return cityList.get(mPosition);
    }

    private void initWidget() {
        mEditText = (EditText) findViewById(R.id.loc_editText);
        mTitle = (TextView) findViewById(R.id.loc_title);
        mBtnClose = (ImageView) findViewById(R.id.loc_btn_close);
        mBtnSearch = (ImageView) findViewById(R.id.loc_btn_search);
        mListview = (ListView) findViewById(R.id.loc_listView);
        mListview.setDivider(null);

    }

    /**
     * cityList에 GeoInfo data setting
     */
    private void setData() {
        cityList = new ArrayList<>();
        // 시청기준
        cityList.add(new GeoInfo("서울특별시", 37.566481 + "", 126.977925 + "", 60 + "", 127 + ""));
        cityList.add(new GeoInfo("경기도", 37.274875 + "", 127.009444 + "", 60 + "", 120 + ""));
        cityList.add(new GeoInfo("강원도", 37.885644 + "", 127.729797 + "", 73 + "", 134 + ""));
        cityList.add(new GeoInfo("충청북도", 36.635655 + "", 127.491515 + "", 69 + "", 107 + ""));
        cityList.add(new GeoInfo("충청남도", 36.724555 + "", 126.669625 + "", 55 + "", 108 + ""));
        cityList.add(new GeoInfo("전라북도", 35.835013 + "", 127.108407 + "", 63 + "", 89 + ""));
        cityList.add(new GeoInfo("전라남도", 34.920512 + "", 126.471468 + "", 51 + "", 69 + ""));
        cityList.add(new GeoInfo("경상북도", 36.576318 + "", 128.505750 + "", 87 + "", 106 + ""));
        cityList.add(new GeoInfo("경상남도", 35.345703 + "", 128.712331 + "", 91 + "", 79 + ""));
        cityList.add(new GeoInfo("제주도", 33.489210 + "", 126.498379 + "", 52 + "", 38 + ""));
    }

    /**
     * ArrayAdapter setting
     */
    private void setmListview() {
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_selectable_list_item);
        adapter.add("서울특별시");
        adapter.add("경기도");
        adapter.add("강원도");
        adapter.add("충청북도");
        adapter.add("충청남도");
        adapter.add("전라북도");
        adapter.add("전라남도");
        adapter.add("경상북도");
        adapter.add("경상남도");
        adapter.add("제주도");
        setListViewOnClick();
        mListview.setAdapter(adapter);
    }

    private void setListViewOnClick() {
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPosition = position;
                Toast.makeText(context, "[" + cityList.get(position).getCityName() + "]가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                setMListner();
                dismiss();
            }
        });
    }

    /**
     * dialog.show(); 할 때, DIM 효과를 주기 위해 처리
     */
    private void setWindowManager() {
        WindowManager.LayoutParams window = new WindowManager.LayoutParams();
        window.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.dimAmount = 0.5f;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(window);
    }

    /**
     * TextWatcher를 활용하여 ArrayAdapter 검색 기능
     * ===== TODO : index 반영 처리
     */
    private void setFilerOnEdit() {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable edit) {
                String filterTxt = edit.toString();
                if (filterTxt.length() > 0) {
                    mListview.setFilterText(String.valueOf(filterTxt));
//                    for(GeoInfo temp : cityList){
//                        if (temp.getCityName().contains(filterTxt)){
//                            mListner.shareValue(temp);
//                            dismiss();
//                        }
//                    }
                } else {
                    mListview.clearTextFilter();
                }
            }
        });
    }

    private void setBtnOnClick() {
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "서치 동작 완료", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setmBtnSearch(){

    }

    /**
     * 받은 geoInfo 데이터를 보내주기 위한 interface인 CustomDiaListener 작성
     */
    public interface CustomDiaListener {
        public void shareValue(GeoInfo geoInfo);
    }
}

