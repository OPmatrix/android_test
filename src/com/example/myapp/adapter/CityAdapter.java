package com.example.myapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.myapp.views.City;
import com.example.myapp.views.City_;

/**
 * Created by ouyangzhouchao on 14-7-13.
 */
public class CityAdapter extends ArrayAdapter<String> {
    private Context context;
    private int selected = -1;
    private String[] list;
    private City lastSelected = null;

    public CityAdapter(Context context) {
        super(context, 0);
        this.context = context;
    }

    public void setList(String[] list) {
        this.list = list;
    }

    public void setSelected(int position) {
        selected = position;
    }

    public City getLastSelected() {
        return lastSelected;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city;
        if (convertView == null) {
            city = City_.build(context);
        } else {
            city = (City) convertView;
        }

        if (selected >= 0 && selected == position && lastSelected == null) {
            lastSelected = city;
            city.bind(getItem(position), true);
        } else {
            city.bind(getItem(position), false);
        }

        return city;
    }
}
