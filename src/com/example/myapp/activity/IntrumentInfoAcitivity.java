package com.example.myapp.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;
import com.example.myapp.R;

/**
 * Created by ouyangzhouchao on 14-6-9.
 */
public class IntrumentInfoAcitivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intrument_info);
        TextView instrumentId = (TextView)findViewById(R.id.instrument_id);
        instrumentId.setText(getAndroidId(this));

        TextView metric = (TextView)findViewById(R.id.metric);
        metric.setText(getMetric(this));

        TextView density = (TextView)findViewById(R.id.density);
        density.setText(getDensity(this));
    }

    public static String getDensity(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return String.valueOf(density);
    }

    public static String getMetric(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return width + " X " +height;
    }

    public static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }
}
