package com.example.myapp.views;


import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.myapp.R;
import com.example.myapp.adapter.CityAdapter;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.province_item)
public class Province extends RelativeLayout {
    @ViewById
    TextView provider;

    @ViewById
    GridView cities;

    public Province(Context context) {
        super(context);
    }

    public GridView getDetails() {
        return cities;
    }

    public TextView getProvider() {
        return provider;
    }

    public void bind(String province, CityAdapter cityAdapter, AdapterView.OnItemClickListener onItemClickListener) {
        provider.setText(province);
        if (cityAdapter == null) {
            return;
        }

        if(cityAdapter != null) {
            cities.setAdapter(cityAdapter);
            cities.setVisibility(View.VISIBLE);
        }

        cities.setOnItemClickListener(onItemClickListener);
    }
}