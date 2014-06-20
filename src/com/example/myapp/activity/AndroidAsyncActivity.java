package com.example.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myapp.R;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.socketio.*;
import org.androidannotations.annotations.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ouyangzhouchao on 14-6-16.
 */
@EActivity(R.layout.android_async)
public class AndroidAsyncActivity extends Activity {

    @ViewById(R.id.content)
    TextView content;
    @ViewById(R.id.sendMsg)
    Button sendMsg;
    @ViewById(R.id.input)
    EditText input;
    @ViewById(R.id.userName)
    EditText userName;
    @Extra("name")
    String name;
    @Extra("room")
    String room;

    private SocketIOClient socketIOClient;


    @AfterViews
    void afterView() {
        initSocketConnection();
    }

    @Click(R.id.sendMsg)
    void sendMsg() {
        if (socketIOClient == null) {
            return;
        }
        String text = input.getText().toString();
        String to = userName.getText().toString();
        JSONArray args = new JSONArray();
        if (to.equals("")) {
            args.put(text);
            socketIOClient.emit("roomChat", args, null);
        } else {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("content", text);
                jsonObject.put("to", to);
                args.put(jsonObject);
                socketIOClient.emit("privateChat", args, null);
            } catch (JSONException e) {
                Log.e("", "", e);
            }
        }
    }

    private void initSocketConnection() {
        SocketIOClient.connect(AsyncHttpClient.getDefaultInstance(), "http://ping.3g.cn:8080/", new ConnectCallback() {
            @Override
            public void onConnectCompleted(Exception ex, SocketIOClient client) {
                if (ex != null) {
                    Log.e("app", "app", ex);
                    return;
                }
                socketIOClient = client;
                socketIOClient.setStringCallback(new StringCallback() {
                    @Override
                    public void onString(String string, Acknowledge acknowledge) {
                        showMsg(string);
                        Log.e("onString", string);
                    }
                });
                socketIOClient.on("roomChat", new EventCallback() {
                    @Override
                    public void onEvent(JSONArray arguments, Acknowledge acknowledge) {
                        try {
                            String content = (String) arguments.get(0);
                            showMsg(content);
                            Log.e("onEvent", "roomChat: " + arguments.toString());
                        } catch (Exception e) {

                        }

                    }
                });
                socketIOClient.on("privateChat", new EventCallback() {
                    @Override
                    public void onEvent(JSONArray arguments, Acknowledge acknowledge) {
                        try {
                            String content = (String) arguments.get(0);
                            showMsg(content);
                            Log.e("onEvent", "privateChat: " + arguments.toString());
                        } catch (Exception e) {

                        }

                    }
                });
                socketIOClient.setJSONCallback(new JSONCallback() {
                    @Override
                    public void onJSON(JSONObject json, Acknowledge acknowledge) {
                        showMsg(json.toString());
                        Log.e("onJSON", "json: " + json.toString());
                    }
                });
                login();
            }
        });
    }

    private void login() {
        try {
            JSONArray args = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", name);
            jsonObject.put("roomToken", room);
            args.put(jsonObject);
            socketIOClient.emit("login", args, null);
        } catch (JSONException ex) {
            Log.e("app", "app", ex);
        }
    }

    private void showMsg(String str) {
        Bundle data = new Bundle();
        data.putString("content", str);
        Message message = new Message();
        message.setData(data);
        uiHandler.sendMessage(message);
    }

    private Handler uiHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            Bundle data = msg.getData();
            content.setText(content.getText() + "\n" + data.getString("content"));
        }

    };

}
