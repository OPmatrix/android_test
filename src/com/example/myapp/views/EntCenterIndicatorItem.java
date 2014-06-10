package com.example.myapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.myapp.R;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;


/**
 * Created by oyzc on 3/21/14.
 */
@EViewGroup(R.layout.obtain_query_title)
public class EntCenterIndicatorItem extends LinearLayout {

    @ViewById(R.id.method)
    TextView itemsTextView;

    public EntCenterIndicatorItem(Context context) {
        super(context);
    }

    public EntCenterIndicatorItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void bindData(String item) {
        itemsTextView.setText(item);
    }

    public void setIndicatorActive() {
        itemsTextView.setTextColor(getResources().getColor(R.color.view_pager_indicator_dark_blue));
    }

    public void setIndicatorUnActive() {
        itemsTextView.setTextColor(getResources().getColor(R.color.view_pager_indicator_gray));
    }
}
