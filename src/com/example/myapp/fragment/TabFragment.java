package com.example.myapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapp.R;
import com.example.myapp.views.EntCenterIndicatorItem;

/**
 * Created by ouyangzhouchao on 14-6-12.
 */
public class TabFragment extends BaseFragment {

    private EntCenterIndicatorItem item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_live, container, false);
        item = (EntCenterIndicatorItem) view.findViewById(R.id.item);
        return view;
    }
}
