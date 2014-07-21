package com.example.myapp.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.myapp.R;
import com.example.myapp.activity.WeatherActivity;
import com.example.myapp.db.Weather;
import com.example.myapp.entity.Setting;
import com.example.myapp.entity.Setting_;
import com.example.myapp.entity.WeatherList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by ouyangzhouchao on 14-7-13.
 */
public class WeatherDao {

    private static final String SEARCH_CITY_URL = "http://goweatherex.3g.cn/goweatherex/city/search?lang=en&sys=4.1.1&ps=2.28&chan=1100&k=";

    private static final String WEATHER_URL_PREFIX = "http://goweatherex.3g.cn/goweatherex/weather/getWeather?lang=zh_CN&ps=2.28&sys=4.0&chan=1100&w=";

    private static boolean isRefreshing = false;

    public static void getWeather(Context context, WeatherActivity.WeatherListener weatherListener) {
        final Setting_ setting = Setting.getInstance(context.getApplicationContext());
        final String city = setting.getCity();
        List<Weather> weathers = null;
        if (!shouldReload(weathers, city)) {
            WeatherList weatherList = new WeatherList(city, weathers);
            weatherListener.onSuccessed(weatherList);
            return;
        }

        getByWeb(city, context, weatherListener, setting);
    }

    public static void getByWeb(final String cityName, final Context context, final WeatherActivity.WeatherListener weatherListener, final Setting_ setting) {
        if (isRefreshing) {
            return;
        }

        final AQuery aq = new AQuery(context);
        isRefreshing = true;
        String defaultCity = context.getResources().getString(R.string.defaultCity);
        String searchURL = SEARCH_CITY_URL + ((cityName == null && cityName.equals("")) ? defaultCity : cityName);
        aq.ajax(searchURL, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {

                //@todo add a toast
                if (json == null) {
                    isRefreshing = false;
                    weatherListener.onFailed();
                    return;
                }

                try {
                    JSONArray cities = json.getJSONArray("cities");
                    if (cities.length() <= 0) {
                        isRefreshing = false;
                        weatherListener.onFailed();
                        return;
                    }
                    JSONObject city = cities.getJSONObject(0);
                    String cityId = city.getString("cityId");
                    aq.ajax(WEATHER_URL_PREFIX + cityId, JSONObject.class, new AjaxCallback<JSONObject>() {
                        @Override
                        public void callback(String url, JSONObject json, AjaxStatus status) {
                            isRefreshing = false;
                            if (json == null) {
                                weatherListener.onFailed();
                                return;
                            }
                            try {
                                final WeatherList weatherList = new WeatherList(json);
                                if (TextUtils.isEmpty(cityName)) {
                                    setting.setCity(weatherList.getCity());
                                }
                                weatherListener.onSuccessed(weatherList);
                            } catch (JSONException e) {
                                weatherListener.onFailed();
                                Log.e("Weather", e.getMessage());
                            }
                        }
                    });
                } catch (JSONException e) {
                    weatherListener.onFailed();
                    Log.e("Weather", e.getMessage());
                }
            }
        });
    }

    private static boolean shouldReload(List<Weather> list, String city) {
        if (list == null) {
            return true;
        }
        if (list.size() != 4) {
            return true;
        }
        if (isOutOfDay(list)) {
            return true;
        }
        if (!list.get(0).getCity().startsWith(city)) {
            return true;
        }

        return false;
    }

    private static boolean isOutOfDay(List<Weather> list) {
        if (list == null || list.size() == 0) {
            return true;
        }

        return new Date().after(new Date(list.get(0).getDate().getTime() + 3600 * 1000));
    }

    //
////    private static List<Weather> getByDb(Context context) {
////        DaoSession session = NewsApplication.getDaoSession(context);
////        List<Weather> weathers = session.loadAll(com.jiubang.app.db.Weather.class);
////        return weathers;
////    }
//
////    private static void save(Context context, WeatherList weatherList) throws ParseException {
////        DaoSession session = NewsApplication.getDaoSession(context);
////        session.deleteAll(com.jiubang.app.db.Weather.class);
////        for (int i = 0; i < weatherList.getList().size(); i++) {
////            Weather source = weatherList.getList().get(i);
////            Weather weather = new Weather();
////            weather.setDate(DATE_FORMAT.parse(weatherList.getTime()));
////            weather.setIntroduction(source.getIntroduction());
////            weather.setCity(weatherList.getCity());
////            weather.setTemperature(source.getTemperature());
////            session.insert(weather);
////        }
////
////    }

}
