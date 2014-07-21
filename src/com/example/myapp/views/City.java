package com.example.myapp.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.myapp.R;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by ouyangzhouchao on 14-7-13.
 */
@EViewGroup(R.layout.city_item)
public class City extends RelativeLayout {
    @ViewById
    TextView city;

    @ViewById
    ImageView selected;

    public City(Context context) {
        super(context);
    }

    public CharSequence getText() {
        return city.getText();
    }

    public void setSelected(boolean value) {
        int state = value ? View.VISIBLE : View.GONE;
        selected.setVisibility(state);
    }

    public void bind(String name, boolean isSelected) {
        city.setText(name);

        if (isSelected) {
            setSelected(isSelected);
        }
    }
}

