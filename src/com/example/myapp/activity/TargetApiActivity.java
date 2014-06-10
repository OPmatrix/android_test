package com.example.myapp.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.myapp.R;

import java.util.List;

/**
 * Created by ouyangzhouchao on 14-6-3.
 */
public class TargetApiActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target_api);
        Button run = (Button)findViewById(R.id.run);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadInfo();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void loadInfo() {
        long installTime ;
        PackageManager pm = this.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        PackageInfo packageInfo = packages.get(0);
        if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null) {
            try {
                installTime = packageInfo.firstInstallTime;
            } catch (Exception e) {
                installTime = 0;
            }
        }
    }
}
