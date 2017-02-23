package com.example.oudehuitong.wyard;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * Created by oudehuitong on 2017/2/22.
 */

public class DEApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        EaseUI.getInstance().init(this,null);
        EMClient.getInstance().setDebugMode(true);
    }
}
