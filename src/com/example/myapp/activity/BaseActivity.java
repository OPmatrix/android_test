package com.example.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by ouyangzhouchao on 14-6-12.
 */
public class BaseActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Activity trace", this.getClass().getName() + "@"+System.identityHashCode(this)+" onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d("Activity trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("Activity trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("Activity trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("Activity trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d("Activity trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onDestroy");
        super.onDestroy();
    }

}

