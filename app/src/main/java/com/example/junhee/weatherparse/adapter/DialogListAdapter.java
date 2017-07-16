package com.example.junhee.weatherparse.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.junhee.weatherparse.R;
import com.example.junhee.weatherparse.domain.GeoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunHee on 2017. 7. 15..
 */

public class DialogListAdapter extends ArrayAdapter {

    private List<GeoInfo> cityList = new ArrayList<>();
    private Context context;
    private View convertView;
//    public CustomDialog.Callback mListener;

    public int mPosition;

    public DialogListAdapter(List<GeoInfo> cityList, Context context) {
        super(context, R.layout.dialog_item_list);
        this.context = context;
        this.cityList = cityList;
    }

//    public CustomDialog.Callback getmListener() {
//        return mListener;
//    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public GeoInfo geoInfo;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        geoInfo = cityList.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.dialog_item_list, parent, false);
        }
        TextView cityName = (TextView) convertView.findViewById(R.id.text1);
        cityName.setText(geoInfo.getCityName());
        Log.e("DialogAdapter", "position : " + position);

        this.convertView = convertView;
        setOnClick(position);
        return convertView;
    }

    private void setOnClick(final int position) {
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("DiaglogAdapter", "=============== onClick position : " + position);
                mPosition = position;
//                mListener.shareValue(mPosition);
                Log.e("DiaglogAdapter", "=============== onClick mPosition : " + mPosition);
            }
        });
    }
}
