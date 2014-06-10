package com.example.myapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.myapp.R;

/**
 * Created by ouyangzhouchao on 14-6-10.
 */
public class PagerItemFragment extends Fragment {

    private String tag;
    public PagerItemFragment(String tag) {
        super();
        this.tag = tag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.pager_item, container, false);
        TextView tagTextView = (TextView)contentView.findViewById(R.id.tag);
        tagTextView.setText(tag);
        return contentView;
    }

}
