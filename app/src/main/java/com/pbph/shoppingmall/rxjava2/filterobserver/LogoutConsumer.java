package com.pbph.shoppingmall.rxjava2.filterobserver;

import android.content.Context;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.shoppingmall.rxjava2.errorlistener.OnErrorLogoutListener;


public abstract class LogoutConsumer<T extends BaseResponesModel> extends BaseConsumer<T> {

    public LogoutConsumer(Context context) {
        this(context, false);
    }

    public LogoutConsumer(Context context, boolean filterAllError) {
        super(context, filterAllError);
//        putDataFilter(411, "请登录", OnErrorLogoutListener.class);
    }
}
