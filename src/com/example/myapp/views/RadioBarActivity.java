package com.example.myapp.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import com.example.myapp.R;

/**
 * indicator for tabhost which uses radioGroup
 * @author ouyangzhouchao
 * @version 0.1.0
 * @since Tue 5 june 2014
 */
public class RadioBarActivity extends Activity implements RadioGroup.OnCheckedChangeListener {

    private int curCheckedId = -1;

    private int fromIndicatorXDelta = 0;
    private float lastXScaleFactor = 1f;

    RadioGroup radioGroup;
    HorizontalScrollView horizontalScrollView;
    ImageView underlineIndicator;
    Button addRadio;
    Button removeRadio;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_bar);
        initView();
        radioGroup.setOnCheckedChangeListener(this);

        addRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int childCount = radioGroup.getChildCount();
                RadioButton radioButton = (RadioButton) LayoutInflater.from(RadioBarActivity.this).inflate(R.layout.radio_button, null);
                String showText = "button";
                for (int i = 0; i < childCount; i++) {
                    showText += "  " + i;
                }
                radioButton.setText(showText);
                radioButton.setId(childCount);
                radioGroup.addView(radioButton, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                if (curCheckedId == -1) {
                    curCheckedId = 0;
                    RelativeLayout.LayoutParams indicatorLayout = (RelativeLayout.LayoutParams) underlineIndicator.getLayoutParams();
                    radioButton.measure(0, 0);
                    indicatorLayout.width = radioButton.getMeasuredWidth();
                    underlineIndicator.setLayoutParams(indicatorLayout);
                }
            }
        });

        removeRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int childCount = radioGroup.getChildCount();
                if (childCount >= 0) {
                    radioGroup.removeView(radioGroup.getChildAt(childCount - 1));
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (curCheckedId == checkedId) {
            return;
        }
        curCheckedId = checkedId;
        int toIndicatorXDelta = 0;
        for (int i = 0; i < curCheckedId; i++) {
            toIndicatorXDelta += radioGroup.getChildAt(i).getWidth();
        }

        View curRadio = radioGroup.getChildAt(checkedId);
        AnimationSet _AnimationSet = new AnimationSet(true);

        ScaleAnimation _ScaleAnimation = new ScaleAnimation(lastXScaleFactor, (float) curRadio.getWidth() / underlineIndicator.getWidth(), 1f, 1f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.5f);
        lastXScaleFactor = (float) curRadio.getWidth() / underlineIndicator.getWidth();
        _ScaleAnimation.setDuration(200);
        _AnimationSet.addAnimation(_ScaleAnimation);
        TranslateAnimation _TranslateAnimation = new TranslateAnimation(fromIndicatorXDelta, toIndicatorXDelta, 0f, 0f);
        _TranslateAnimation.setDuration(200);
        _AnimationSet.addAnimation(_TranslateAnimation);
        _AnimationSet.setFillAfter(true);
        underlineIndicator.startAnimation(_AnimationSet);

        fromIndicatorXDelta = toIndicatorXDelta;
        horizontalScrollView.smoothScrollTo(toIndicatorXDelta-100, 0);
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        underlineIndicator = (ImageView) findViewById(R.id.underlineIndicator);
        addRadio = (Button) findViewById(R.id.addRadio);
        removeRadio = (Button) findViewById(R.id.removeRadio);
    }

}
