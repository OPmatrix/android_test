package com.example.myapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapp.R;
import org.androidannotations.annotations.*;

import java.util.Date;

/**
 * Created by ouyangzhouchao on 14-7-11.
 */

@EActivity(R.layout.location)
public class LocationActivity extends Activity {

    @ViewById(R.id.latitude)
    TextView latitudeView;
    @ViewById(R.id.longitude)
    TextView longitudeView;
    @ViewById(R.id.altitude)
    TextView altitudeView;
    @ViewById(R.id.tips)
    TextView tips;
    @ViewById
    Button refresh;

    LocationManager locationManager;

    @AfterViews
    void afterViews() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location == null) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000, 0, locationListener);
        } else {
            locationListener.onLocationChanged(location);
        }
    }

    @Click
    void refresh() {
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                1000, 0, locationListener);
    }

    @UiThread
    void showLocation(Location location) {
        double latitude = location.getLatitude();     //经度
        double longitude = location.getLongitude(); //纬度
        double altitude = location.getAltitude();     //海拔
        latitudeView.setText(String.valueOf(latitude));
        longitudeView.setText(String.valueOf(longitude));
        altitudeView.setText(String.valueOf(altitude));
        Toast.makeText(this,  new Date(location.getTime()) + "", Toast.LENGTH_SHORT).show();
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) { //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
            Toast.makeText(LocationActivity.this, "onLocationChanged", Toast.LENGTH_SHORT).show();
            // log it when the location changes
            if (location != null) {
                showLocation(location);
                locationManager.removeUpdates(locationListener);
            }
        }

        public void onProviderDisabled(String provider) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
            Toast.makeText(LocationActivity.this, "onProviderDisabled", Toast.LENGTH_SHORT).show();
        }

        public void onProviderEnabled(String provider) {
            Toast.makeText(LocationActivity.this, "onProviderEnabled", Toast.LENGTH_SHORT).show();
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Provider的转态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
            Toast.makeText(LocationActivity.this, "onStatusChanged", Toast.LENGTH_SHORT).show();
            tips.setText(status);
        }
    };


}
