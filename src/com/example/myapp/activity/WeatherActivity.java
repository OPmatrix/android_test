package com.example.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapp.R;
import com.example.myapp.common.WeatherDao;
import com.example.myapp.entity.Setting;
import com.example.myapp.entity.Setting_;
import com.example.myapp.entity.WeatherList;
import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.StringRes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by ouyangzhouchao on 14-7-3.
 */

@EActivity(R.layout.weather)
public class WeatherActivity extends Activity {

    @ViewById(R.id.root)
    View container;
    @ViewById
    TextView temperature;
    @ViewById
    TextView introduce;
    @ViewById
    TextView location;
    @ViewById
    ImageView flag;
    @ViewById
    TextView tomorrowTemperature;
    @ViewById
    TextView tomorrow;
    @ViewById
    ImageView back;
    @ViewById
    TextView afterTomorrowTemperature;
    @ViewById
    TextView afterTomorrow;
    @ViewById
    View refresh;
    @ViewById
    TextView updateTime;
    @ViewById
    TextView thirdDayTemperature;
    @ViewById
    TextView thirdDay;
    @ViewById
    View split;
    @ViewById
    ProgressBar loadingProgressbar;

    @StringRes
    String defaultCity;

    WeatherListener weatherListener;
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
    private boolean isBackgroundSet = false;

    @AfterViews
    void bindData() {
        weatherListener = new WeatherListener() {
            @Override
            public void onSuccessed(WeatherList weatherList) {
                hideLoading();

                if (weatherList == null || weatherList.getList().size() < 4) {
                    Toast.makeText(getApplicationContext(), "未知错误",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                show();
                updateTime.setText(getDate(weatherList));
                setBackground(weatherList);
                bindToday(weatherList);
                bind(weatherList, tomorrowTemperature, tomorrow, 1);
                bind(weatherList, afterTomorrowTemperature, afterTomorrow, 2);
                bind(weatherList, thirdDayTemperature, thirdDay, 3);
            }

            @Override
            public void onFailed(){
                hideLoading();
                Toast.makeText(getApplicationContext(), "未知错误",
                        Toast.LENGTH_SHORT).show();
                return;
            }
        };

        setBackground();
        showLoading();
        getWeather();
    }

    @Click
    void back() {
        finish();
    }

    @Click
    void refresh() {
        final Setting_ setting = Setting.getInstance(getApplicationContext());
        final String city = setting.getCity();

        showLoading();
        WeatherDao.getByWeb(city, this, weatherListener, setting);
    }

    @Click
    void location() {
        Intent intent = new Intent(this, CitySelectActivity_.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getCity().startsWith(location.getText().toString())) {
            return;
        }

        showLoading();
        WeatherDao.getByWeb(getCity(), this, weatherListener, Setting.getInstance(getApplicationContext()));
    }

    private void bindToday(WeatherList weatherList) {
        com.example.myapp.entity.Weather today = weatherList.getList().get(0);
        temperature.setText(today.getTemperature());
        introduce.setText(today.getIntroduction());
        location.setText(weatherList.getCity());
        flag.setImageResource(today.getLarge());
    }

    private void bind(WeatherList list, TextView temperature, TextView introduce, int index) {
        com.example.myapp.entity.Weather weather = list.getList().get(index);
        temperature.setText(weather.getIntroduction().split("~")[0]);
        temperature.setCompoundDrawablesWithIntrinsicBounds(0, weather.getSmall(), 0, 0);
        introduce.setText(getWeek(index) + " " + weather.getTemperature());
    }

    private void show() {
        temperature.setVisibility(View.VISIBLE);
        introduce.setVisibility(View.VISIBLE);
        location.setVisibility(View.VISIBLE);
        flag.setVisibility(View.VISIBLE);
        back.setVisibility(View.VISIBLE);
        refresh.setVisibility(View.VISIBLE);
        updateTime.setVisibility(View.VISIBLE);
        split.setVisibility(View.VISIBLE);
        tomorrow.setVisibility(View.VISIBLE);
        tomorrowTemperature.setVisibility(View.VISIBLE);
        afterTomorrow.setVisibility(View.VISIBLE);
        afterTomorrowTemperature.setVisibility(View.VISIBLE);
        thirdDay.setVisibility(View.VISIBLE);
        thirdDayTemperature.setVisibility(View.VISIBLE);
    }

    private String getDate(WeatherList list) {
        return "3G天气：更新于" + list.getTime() + " " + getWeek(0);
    }

    private String getWeek(int index) {
        final String prefix = "周";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (index > 0) {
            calendar.add(Calendar.DATE, index);
        }

        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case (Calendar.MONDAY): {
                return prefix + "一";
            }
            case (Calendar.TUESDAY): {
                return prefix + "二";
            }
            case (Calendar.WEDNESDAY): {
                return prefix + "三";
            }
            case (Calendar.THURSDAY): {
                return prefix + "四";
            }
            case (Calendar.FRIDAY): {
                return prefix + "五";
            }
            case (Calendar.SUNDAY): {
                return prefix + "日";
            }
            default: {
                return prefix + "六";
            }
        }
    }

    @Background
    void getWeather() {
        WeatherDao.getWeather(this, weatherListener);
    }

    private String getCity() {
        final Setting_ setting = Setting.getInstance(getApplicationContext());
        final String city = setting.getCity();
        return city;
    }


    private void showLoading() {
        loadingProgressbar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        loadingProgressbar.setVisibility(View.GONE);
    }

    private void setBackground() {
        if (com.example.myapp.entity.Weather.isNight()) {
            setBackground(R.drawable.night_bg);
            return;
        }

        setBackground(R.drawable.sun_bg);
    }

    private void setBackground(WeatherList weatherList) {
        if (isBackgroundSet) {
            return;
        }

        final String RAIN = "雨";
        com.example.myapp.entity.Weather today = weatherList.getList().get(0);
        if (today.getIntroduction().contains(RAIN)) {
            setBackground(R.drawable.rain_bg);
            return;
        }
        if (com.example.myapp.entity.Weather.isNight()) {
            setBackground(R.drawable.night_bg);
            return;
        }

        setBackground(R.drawable.sun_bg);
    }

    private void setBackground(int id) {
        com.example.myapp.utils.Background.set(this, container, id);
    }

////    @Override
////    public void setDay() {
////        super.setDay();
////        isBackgroundSet = false;
////        setBackground();
////    }
////
////    @Override
////    public void setNight() {
////        super.setNight();
////        isBackgroundSet = true;
////        setBackground(R.drawable.weather_night);
////    }
//
    public interface WeatherListener {
        void onSuccessed(WeatherList weatherList);
        void onFailed();
    }

}
