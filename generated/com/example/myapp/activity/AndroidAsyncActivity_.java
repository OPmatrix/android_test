//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.
//


package com.example.myapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myapp.R.id;
import com.example.myapp.R.layout;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class AndroidAsyncActivity_
    extends AndroidAsyncActivity
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    public final static String ROOM_EXTRA = "room";
    public final static String NAME_EXTRA = "name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.android_async);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        injectExtras_();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static AndroidAsyncActivity_.IntentBuilder_ intent(Context context) {
        return new AndroidAsyncActivity_.IntentBuilder_(context);
    }

    public static AndroidAsyncActivity_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new AndroidAsyncActivity_.IntentBuilder_(fragment);
    }

    public static AndroidAsyncActivity_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new AndroidAsyncActivity_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        numContent = ((TextView) hasViews.findViewById(id.numContent));
        content = ((TextView) hasViews.findViewById(id.content));
        userName = ((EditText) hasViews.findViewById(id.userName));
        input = ((EditText) hasViews.findViewById(id.input));
        sendMsg = ((Button) hasViews.findViewById(id.sendMsg));
        {
            View view = hasViews.findViewById(id.sendMsg);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        AndroidAsyncActivity_.this.sendMsg();
                    }

                }
                );
            }
        }
        afterView();
    }

    private void injectExtras_() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_!= null) {
            if (extras_.containsKey(ROOM_EXTRA)) {
                room = extras_.getString(ROOM_EXTRA);
            }
            if (extras_.containsKey(NAME_EXTRA)) {
                name = extras_.getString(NAME_EXTRA);
            }
        }
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        injectExtras_();
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private android.app.Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, AndroidAsyncActivity_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            fragment_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, AndroidAsyncActivity_.class);
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, AndroidAsyncActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public AndroidAsyncActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent_, requestCode);
            } else {
                if (fragment_!= null) {
                    fragment_.startActivityForResult(intent_, requestCode);
                } else {
                    if (context_ instanceof Activity) {
                        ((Activity) context_).startActivityForResult(intent_, requestCode);
                    } else {
                        context_.startActivity(intent_);
                    }
                }
            }
        }

        public AndroidAsyncActivity_.IntentBuilder_ room(String room) {
            intent_.putExtra(ROOM_EXTRA, room);
            return this;
        }

        public AndroidAsyncActivity_.IntentBuilder_ name(String name) {
            intent_.putExtra(NAME_EXTRA, name);
            return this;
        }

    }

}
