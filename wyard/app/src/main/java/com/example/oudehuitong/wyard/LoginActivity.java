package com.example.oudehuitong.wyard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by oudehuitong on 2017/2/22.
 */

public class LoginActivity extends Activity implements View.OnClickListener{
    private EditText zh;
    private EditText mm;
    private Button register;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        zh= (EditText) findViewById(R.id.et_zh);
        mm= (EditText) findViewById(R.id.et_mm);
        register= (Button) findViewById(R.id.bt_zc);
        login= (Button) findViewById(R.id.bt_dl);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_zc:
                    signout();
                break;
            case R.id.bt_dl:
                    signin();
                break;
        }
    }
    private void signout(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(zh.getText().toString().trim(),
                            mm.getText().toString().trim());
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.i("2345","注册失败"+e.getErrorCode()+","+e.getMessage());
                }
            }
        }).start();
    }
    private void signin(){
        EMClient.getInstance().login(zh.getText().toString().trim(),
                mm.getText().toString().trim(), new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.e("2345","登录失败"+i+","+s);
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
    }

}
