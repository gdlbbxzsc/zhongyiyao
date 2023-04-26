package com.pbph.shoppingmall.utils.ui;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/4/8.
 */

public class CountdownHelper1 implements Runnable {

    public static CountdownHelper1 getInstance() {
        return InnerInstance.INSTANCE;
    }


    private static class InnerInstance {
        private static CountdownHelper1 INSTANCE = new CountdownHelper1();
    }

    ConcurrentHashMap<TextView, Boolean> map = new ConcurrentHashMap<>();

    private CountdownHelper1() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        while (true) {

            if (map.size() <= 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.sendEmptyMessage(0);
        }
    }

    public void put(TextView tv) {
        notifyAll();
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };


}
