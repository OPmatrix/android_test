package com.example.myapp.activity;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.myapp.R;
import com.example.myapp.adapter.CityAdapter;
import com.example.myapp.adapter.ProvinceAdapter;
import com.example.myapp.entity.Setting;
import com.example.myapp.entity.Setting_;
import com.example.myapp.views.City;
import com.example.myapp.views.Province;
import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.StringArrayRes;

/**
 * Created by ouyangzhouchao on 14-7-11.
 */
@NoTitle
@Fullscreen
@EActivity(R.layout.select_city)
public class CitySelectActivity extends Activity implements AdapterView.OnItemClickListener {
    private int lastSelectedPosition = -1;
    private int lineHeight = -1;
    private Province lastSelectedProvince = null;
    private City lastSelectedCity = null;
    private AdapterView.OnItemClickListener onItemClickListener;

    @StringArrayRes(R.array.provinces)
    String[] provinces;

    @StringArrayRes
    String[] Guangdong, Beijing, Shanghai, Tianjing, Chongqing, Hubei, HeBei, Hainan, Heilongliang, Hunan,
            Jilin, Jiangsu, Jiangxi, Liaoning, InnerMongolia, Ningxia, Qinghai, Sichuang, Shandong, Shaanxi, Shanxi, Xizang,
            Yunnan, Zhejiang, Fujian, Ganshu, Guangxi, Guangzhou, Henan, Anhui, taiWan, Hongkong, Macao;


    private String[][] cities;

    @ViewById
    View container;
    @ViewById
    ListView listView;
    @ViewById
    TextView selectedCity;
    private ProvinceAdapter provinceAdapter;
    private CityAdapter cityAdapter;
    private Setting_ setting_;

    @AfterViews
    void init() {
        initCites();
        setting_ = Setting.getInstance(getApplicationContext());
        cityAdapter = new CityAdapter(this);
        provinceAdapter = new ProvinceAdapter(this);

        setAdapter();
    }

    @Click
    void back() {
        finish();
    }

    public void onBackPressed() {
        back();
    }

    private void initCites() {
        cities = new String[][]{
                Guangdong, Beijing, Shanghai, Tianjing, Chongqing, Hubei, HeBei, Hainan, Heilongliang, Hunan,
                Jilin, Jiangsu, Jiangxi, Liaoning, InnerMongolia, Ningxia, Qinghai, Sichuang, Shandong, Shaanxi, Shanxi, Xizang,
                Yunnan, Zhejiang, Fujian, Ganshu, Guangxi, Guangzhou, Henan, Anhui, taiWan, Hongkong, Macao,
        };
    }

    private void setSelectedCity() {
        String currentCity = setting_.getCity();
        if (TextUtils.isEmpty(currentCity)) {
            return;
        }

        int[] index = getIndex(currentCity);
        cityAdapter.setList(cities[index[0]]);
        cityAdapter.setSelected(index[1]);
        provinceAdapter.setSelected(index[0], cityAdapter);
        selectedCity.setText("已选择：" + currentCity);
        listView.setSelection(index[0]);
        lastSelectedPosition = index[0];
    }

    private int[] getIndex(String currentCity) {
        int[] result = new int[2];
        for (int i = 0; i < cities.length; i++) {
            for (int j = 0; j < cities[i].length; j++) {
                if (cities[i][j].startsWith(currentCity)) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }

    private void setAdapter() {
        provinceAdapter.setList(provinces);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hideLastProvince();
                openNewProvince(position);
                storeLastSelected(view, position);
                provinceAdapter.resetHeight(view, cities[position].length);
                listView.setSelection(position);
            }
        });

        provinceAdapter.setOnItemClickListener(this);
        setSelectedCity();
        listView.setAdapter(provinceAdapter);
    }

    private void storeLastSelected(View view, int position) {
        lastSelectedProvince = (Province) view;

        if (lineHeight == -1) {
            lineHeight = lastSelectedProvince.getHeight();
        }

        lastSelectedPosition = position;
    }


    private void openNewProvince(int position) {
        GridView gridView = provinceAdapter.getDetails()[position];
        TextView textView = provinceAdapter.getProviders()[position];
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.show, 0);
        cityAdapter.setList(cities[position]);
        gridView.setAdapter(cityAdapter);
        gridView.setVisibility(View.VISIBLE);
        gridView.setOnItemClickListener(this);
    }


    private City getLastSelectedCity() {
        if (lastSelectedCity == null) {
            return cityAdapter.getLastSelected();
        }

        return lastSelectedCity;
    }

    private Province getLastSelectedProvince() {
        if (lastSelectedProvince == null) {
            return provinceAdapter.getLastSelected();
        }

        return lastSelectedProvince;
    }

    private void hideLastProvince() {
        if (getLastGridView() == null) {
            return;
        }

        getLastGridView().setVisibility(View.GONE);
        getLastTextView().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.hide, 0);
        ViewGroup.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, lineHeight);
        getLastSelectedProvince().setLayoutParams(layoutParams);
    }

    private GridView getLastGridView() {
        if (getLastSelectedProvince() == null) {
            return null;
        }

        return provinceAdapter.getDetails()[lastSelectedPosition];
    }

    private TextView getLastTextView() {
        if (getLastSelectedProvince() == null) {
            return null;
        }

        return provinceAdapter.getProviders()[lastSelectedPosition];
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        City target = (City) view;
        String selectedCityName = (String) target.getText();
        selectedCity.setText("已选择：" + selectedCityName);
        setting_.setCity(selectedCityName);
        if (getLastSelectedCity() != null) {
            getLastSelectedCity().setSelected(false);
        }

        target.setSelected(true);
        lastSelectedCity = target;

        back();
    }

}