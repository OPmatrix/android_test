package com.example.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.myapp.R;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.socketio.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ouyangzhouchao on 14-6-16.
 */
public class AndroidAsyncActivity extends Activity {

    private TextView content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_async);
        content = (TextView) findViewById(R.id.content);
        SocketIOClient.connect(AsyncHttpClient.getDefaultInstance(), "http://192.168.219.142:3000", new ConnectCallback() {
            @Override
            public void onConnectCompleted(Exception ex, SocketIOClient client) {
                if (ex != null) {
                    ex.printStackTrace();
                    return;
                }
                client.setStringCallback(new StringCallback() {
                    @Override
                    public void onString(String string, Acknowledge acknowledge) {
                        System.out.println(string);
                    }
                });
                client.on("someEvent", new EventCallback() {
                    @Override
                    public void onEvent(JSONArray arguments, Acknowledge acknowledge) {
                        System.out.println("args: " + arguments.toString());
                    }
                });
                client.setJSONCallback(new JSONCallback() {
                    @Override
                    public void onJSON(JSONObject json, Acknowledge acknowledge) {
                        content.setText(content.getText() + "\n" + json.toString());
                        System.out.println("json: " + json.toString());
                    }
                });
                try {
                    client.emit(new JSONObject("{\"author\":\"world\",\"time\":\"09-11-14\",\"text\":\"this is just test\",}"));
                } catch (JSONException e) {
                }
            }
        });
    }

}
