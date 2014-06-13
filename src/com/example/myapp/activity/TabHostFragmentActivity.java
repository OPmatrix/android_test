package com.example.myapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost;
import android.widget.TabWidget;
import com.example.myapp.R;
import com.example.myapp.fragment.TabFragment;

/**
 * Created by ouyangzhouchao on 14-6-12.
 */
public class TabHostFragmentActivity extends BaseFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabhost);
        initView();
    }

    private void initView() {
        FragmentTabHost  mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        TabWidget mTabWidget = (TabWidget) findViewById(android.R.id.tabs);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });
        mTabHost.addTab(mTabHost.newTabSpec("live").setIndicator(getLayoutInflater().inflate(R.layout.tab_live_indicator, null)), TabFragment.class, null); //主播
        mTabHost.addTab(mTabHost.newTabSpec("image").setIndicator(getLayoutInflater().inflate(R.layout.tab_image_indicator, null)), TabFragment.class, null); //美图
        mTabHost.addTab(mTabHost.newTabSpec("focus").setIndicator(getLayoutInflater().inflate(R.layout.tab_focus_indicator, null)), TabFragment.class, null); //收藏
        mTabHost.addTab(mTabHost.newTabSpec("user").setIndicator(getLayoutInflater().inflate(R.layout.tab_user_indicator, null)), TabFragment.class, null ); //用户
    }

}
