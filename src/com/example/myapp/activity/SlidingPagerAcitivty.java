package com.example.myapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.example.myapp.R;
import com.example.myapp.fragment.PagerItemFragment;
import com.example.myapp.views.EntCenterIndicatorItem;
import com.example.myapp.views.EntCenterIndicatorItem_;
import com.example.myapp.views.PagerSlidingTabStrip;

/**
 * Created by ouyangzhouchao on 14-6-9.
 */
public class SlidingPagerAcitivty extends FragmentActivity {

    PagerSlidingTabStrip indicators;
    ViewPager pager;

    String[] indicatorItems = {"tab1", "tab2", "tab3", "tab4", "tab5"};

    MyPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_pager);
        pager = (ViewPager) findViewById(R.id.pager);
        indicators = (PagerSlidingTabStrip) findViewById(R.id.indicators);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        indicators.setViewPager(pager);
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter implements PagerSlidingTabStrip.ViewTabProvider {

        private Fragment[] fragments;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);

            fragments = new Fragment[5];
            fragments[0] = new PagerItemFragment("fragment 1  1  1 ");
            fragments[1] = new PagerItemFragment("fragment 2  2  2 ");
            fragments[2] = new PagerItemFragment("fragment 3  3  3 ");
            fragments[3] = new PagerItemFragment("fragment 4  4  4 ");
            fragments[4] = new PagerItemFragment("fragment 5  5  5 ");
        }

        @Override
        public View getPageView(int position) {
            EntCenterIndicatorItem entCenterIndicatorItem = EntCenterIndicatorItem_.build(indicators.getContext());
            entCenterIndicatorItem.bindData(indicatorItems[position]);
            return entCenterIndicatorItem;
        }

        @Override
        public void setSelected(int position) {
            EntCenterIndicatorItem view = (EntCenterIndicatorItem) indicators.getTab(position);
            view.setIndicatorActive();
        }

        @Override
        public void setUnSelected(int position) {
            EntCenterIndicatorItem view = (EntCenterIndicatorItem) indicators.getTab(position);
            view.setIndicatorUnActive();
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }

}
