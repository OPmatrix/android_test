package com.example.myapp.entity;


import com.example.myapp.activity.WeatherActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherList {
    private String city = "";

    public String getCity() {
        return city;
    }

    public String getTime() {
        return time;
    }

    public List<Weather> getList() {
        return list;
    }

    private String time = "";
    private List<Weather> list = new ArrayList<Weather>(4);

    public WeatherList(JSONObject json) throws JSONException {

        city = json.getJSONObject("weather").getJSONObject("city").getString("city");
        time = json.getString("updateTime").substring(10);

        JSONArray jsonArray = json.getJSONObject("weather").getJSONArray("forecasts");
        for (int i = 0; i < jsonArray.length(); i++) {
            Weather weather = new Weather(jsonArray.getJSONObject(i));
            list.add(weather);
        }
    }


    public WeatherList(String city, List<com.example.myapp.db.Weather> list) {
        this.city = city;
        time = WeatherActivity.DATE_FORMAT.format(list.get(0).getDate());

        for (int i = 0; i < list.size(); i++) {
            com.example.myapp.db.Weather source = list.get(i);
            Weather weather = new Weather(source);
            this.list.add(weather);
        }
    }
}

