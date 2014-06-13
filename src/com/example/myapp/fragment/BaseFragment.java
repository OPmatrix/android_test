package com.example.myapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ouyangzhouchao on 14-6-12.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        Log.d("fragment trace", this.getClass().getName() + "@"+System.identityHashCode(this)+" onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("fragment trace", this.getClass().getName() + "@"+System.identityHashCode(this)+" onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("fragment trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("fragment trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d("fragment trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("fragment trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("fragment trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("fragment trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("fragment trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("fragment trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("fragment trace", this.getClass().getName() + "@" + System.identityHashCode(this) + " onDetach");
        super.onDetach();
    }


}
