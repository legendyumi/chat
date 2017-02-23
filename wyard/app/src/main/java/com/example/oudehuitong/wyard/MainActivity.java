package com.example.oudehuitong.wyard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.adapter.message.EMAMessage;
import com.hyphenate.easeui.EaseConstant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText zh;
    private Button register;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zh = (EditText) findViewById(R.id.et_name);
        register = (Button) findViewById(R.id.bt_lt);
        login = (Button) findViewById(R.id.bt_tc);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_lt:
                Intent intent = new Intent(this, ChatActivity.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID, zh.getText().toString().trim());
                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                startActivity(intent);
                break;
            case R.id.bt_tc:
                signout();
                break;
        }
    }

    private void startChat() {
    }

    private void signout() {
        EMClient.getInstance().logout(false, new EMCallBack() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }

            @Override
            public void onError(int i, String s) {
                Log.e("", "登录失败" + i + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

}
