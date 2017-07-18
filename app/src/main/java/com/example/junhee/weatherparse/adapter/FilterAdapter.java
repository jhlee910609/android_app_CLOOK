package com.example.junhee.weatherparse.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.junhee.weatherparse.R;

/**
 * Created by JunHee on 2017. 7. 18..
 */

public class FilterAdapter extends ArrayAdapter<CharSequence> {

    CharSequence[] items;


    public FilterAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull CharSequence[] objects) {
        super(context, resource, objects);
        this.items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int pos, View converview, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mySpinner = inflater.inflate(R.layout.spinner_list, parent, false);
        TextView item = (TextView) mySpinner.findViewById(R.id.text1);
        item.setText(items[pos]);

        return mySpinner;
    }
}
