package com.example.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import com.example.myapp.R;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by ouyangzhouchao on 14-6-20.
 */
@EActivity(R.layout.chat_login)
public class ChatLoginActivity extends Activity{

    @ViewById(R.id.name)
    EditText name;
    @ViewById(R.id.room)
    EditText room;

    @Click(R.id.login)
    void login(){
        Intent intent = new Intent(ChatLoginActivity.this, AndroidAsyncActivity_.class);
        Bundle data = new Bundle();
        data.putCharSequence("name", name.getText().toString());
        data.putCharSequence("room", "room"  + room.getText().toString());
        intent.putExtras(data);
        startActivity(intent);

    }
}
