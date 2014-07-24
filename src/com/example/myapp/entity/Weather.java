package com.example.myapp.entity;


import com.example.myapp.R;
import com.example.myapp.utils.AppUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class Weather {
    private final String SUN = "晴";
    private final String RAIN = "雨";
    private final String SNOW = "雪";
    private final String CLOUDY = "云";
    private int small;
    private int large;
    private String temperature;

    public String getIntroduction() {
        return introduction;
    }

    public String getTemperature() {
        return temperature;
    }

    private String introduction;

    public Weather(JSONObject json) throws JSONException {
        int low = json.getInt("low");
        int high = json.getInt("high");
        temperature = AppUtils.fahrenheit2Celsius(low) + "℃~" +AppUtils.fahrenheit2Celsius(high) + "℃";
        introduction = json.getString("status");
        setImage(introduction);
    }

    public Weather(com.example.myapp.db.Weather weather) {
        temperature = weather.getTemperature();
        introduction = weather.getIntroduction();
        setImage(introduction);
    }

    public int getSmall() {
        return small;
    }

    public int getLarge() {
        return large;
    }


    public void setImage(String key) {
        boolean isNight = isNight();
        if (key.contains(SUN)) {
            large = isNight ? R.drawable.sun_night_large : R.drawable.sun_large;
            small = isNight ? R.drawable.sun_night_small : R.drawable.sun_small;
            return;
        }
        if (key.contains(RAIN)) {
            large = R.drawable.rain_large;
            small = R.drawable.rain_small;
            return;
        }
        if (key.contains(CLOUDY)) {
            large = isNight ? R.drawable.cloudy_ngiht_large : R.drawable.cloudy_day_large;
            small = isNight ? R.drawable.cloudy_ngiht_small : R.drawable.cloudy_day_small;
            return;
        }
        if (key.contains(SNOW)) {
            large = R.drawable.snow_large;
            small = R.drawable.snow_small;
            return;
        }

        large = R.drawable.fog_large;
        small = R.drawable.fog_small;
    }

    public static boolean isNight() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 18 || (hour >= 0 && hour <= 5)) {
            return true;
        }

        return false;
    }
}
