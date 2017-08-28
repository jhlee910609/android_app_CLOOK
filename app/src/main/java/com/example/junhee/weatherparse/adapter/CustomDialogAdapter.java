package com.example.junhee.weatherparse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.junhee.weatherparse.R;
import com.example.junhee.weatherparse.domain.GeoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunHee on 2017. 7. 24..
 */

public class CustomDialogAdapter extends BaseAdapter implements Filterable {

    private List<GeoInfo> cityList = new ArrayList<>();
    private List<GeoInfo> filteredList = new ArrayList<>();
    private ListFilter listFilter;

    private GeoInfo selectedGeoInfo = null;
    public DialogCallback dialogCallback = null;

    // == 기존 코드 ==
    private String filterTxt = "";
    public String selectedCity = "";
    private TextView tv;
    private Context mContext;

    public CustomDialogAdapter(List<GeoInfo> cityList, Context context, DialogCallback dialogCallback) {
        this.cityList = cityList;
        this.filteredList = cityList;
        this.mContext = context;
        this.dialogCallback = dialogCallback;
    }

    public GeoInfo getSelectedGeoInfo() {
        return selectedGeoInfo;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_dialog_item, parent, false);
        }

        tv = (TextView) convertView.findViewById(R.id.custom_list_txt);
        tv.setText(filteredList.get(position).getCityName());

        /* 뷰에 온클릭 달기 */
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGeoInfo = filteredList.get(pos);

                // 인터페이스를 사용하여 데이터를 전달하도록 하였다.
                dialogCallback.getData(selectedGeoInfo);
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter();
        }
        return listFilter;
    }

    // Filter를 상속받아 검색어를 필터링하여 ListView에 보여줄 수 있도록 하였다.
    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                results.values = cityList;
                results.count = cityList.size();

            } else {
                ArrayList<GeoInfo> itemList = new ArrayList<>();

                for (GeoInfo temp : cityList) {
                    if (temp.getCityName().contains(constraint.toString())) {
                        itemList.add(temp);
                    }
                }
                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<GeoInfo>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    public interface DialogCallback {
        public void getData(GeoInfo geoInfo);
    }
}

