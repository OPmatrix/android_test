package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.myapp.views.RadioBarActivity;
import com.example.myapp.views.TargetApiActivity;

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

    }
}
