package com.pbph.shoppingmall.utils.ui;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/4/8.
 */

public class CountdownHelper extends TimerTask {

    public static CountdownHelper getInstance() {
        return InnerInstance.INSTANCE;
    }


    private static class InnerInstance {
        private static CountdownHelper INSTANCE = new CountdownHelper();
    }

    private Timer mTimer = null;


    private CountdownHelper() {
        if (mTimer == null) {
            mTimer = new Timer();
            mTimer.scheduleAtFixedRate(this, 0, 1000);
        }
    }

    @Override
    public void run() {
        handler.sendEmptyMessage(0);
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    Set<TextView> set = new HashSet<>();

    public void destroy() {
        this.cancel();
        mTimer.cancel();
        mTimer = null;
    }
}
