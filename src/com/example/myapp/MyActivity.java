package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.myapp.activity.*;

public class MyActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button targetApi = (Button)findViewById(R.id.targetApi);
        targetApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyActivity.this, TargetApiActivity.class);
                startActivity(intent);
            }
        });

        Button radioBar = (Button)findViewById(R.id.radioBar);
        radioBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyActivity.this, RadioBarActivity.class);
                startActivity(intent);
            }
        });

        Button instrumentInfo  = (Button)findViewById(R.id.instrumentInfo);
        instrumentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyActivity.this, IntrumentInfoAcitivity.class);
                startActivity(intent);
            }
        });

        Button slidingPager  = (Button)findViewById(R.id.slidingPager);
        slidingPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyActivity.this, SlidingPagerActivity.class);
                startActivity(intent);
            }
        });

        Button tabHost  = (Button)findViewById(R.id.tabHost);
        tabHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyActivity.this, TabHostFragmentActivity.class);
                startActivity(intent);
            }
        });

        Button androidAsync  = (Button)findViewById(R.id.androidAsync);
        androidAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyActivity.this, AndroidAsyncActivity.class);
                startActivity(intent);
            }
        });

    }
}
