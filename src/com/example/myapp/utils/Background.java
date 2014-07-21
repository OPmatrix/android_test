package com.example.myapp.utils;


import android.content.Context;
import android.view.View;

public class Background {
    private Background() {

    }

    public static void set(Context context, View view, int drawableId) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(context.getResources().getDrawable(drawableId));
        } else {
            view.setBackground(context.getResources().getDrawable(drawableId));
        }
    }
}
