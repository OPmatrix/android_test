package com.example.myapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.myapp.views.Province;
import com.example.myapp.views.Province_;

/**
 * Created by ouyangzhouchao on 14-7-13.
 */
public class ProvinceAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] list;
    private GridView[] details;
    private TextView[] providers;
    private CityAdapter cityAdapter;
    private int selected = -1;
    private Province lastSelected = null;
    private AdapterView.OnItemClickListener onItemClickListener;


    public void setSelected(int position, CityAdapter cityAdapter) {
        this.selected = position;
        this.cityAdapter = cityAdapter;
    }

    public ProvinceAdapter(Context context) {
        super(context, 0);
        this.context = context;
    }

    public void setList(String[] list) {
        this.list = list;
        details = new GridView[list.length];
        providers = new TextView[list.length];
    }


    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public String getItem(int position) {
        return list[position];
    }

    public GridView[] getDetails() {
        return details;
    }

    public TextView[] getProviders() {
        return providers;
    }

    public Province getLastSelected() {
        return lastSelected;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Province province = Province_.build(context);
//        if (convertView == null) {
//            province = Province_.build(context);
//        } else {
//            province = (Province) convertView;
//        }

        CityAdapter adapter = null;
        if (selected >= 0 && selected == position && lastSelected == null) {
            adapter = cityAdapter;
            lastSelected = province;
        }

        province.bind(getItem(position), adapter, onItemClickListener);
        if (adapter != null) {
            resetHeight(province, cityAdapter.getCount());
        }

        details[position] = province.getDetails();
        providers[position] = province.getProvider();
        return province;
    }

    public void resetHeight(View view, int count) {
        int row = (int) Math.round(count / 2.0);
        int dp = row > 2 ? row * 50 + 50 : row * 50 + 70;
        int height = (int) dip2px(context, dp);
        if (height <= view.getHeight()) {
            return;
        }

        Province province = (Province) view;
        ViewGroup.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        province.setLayoutParams(layoutParams);
    }

    public static float dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);
    }
}

