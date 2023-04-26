package com.pbph.shoppingmall.rxjava2.filterobserver;

import android.content.Context;

import com.pbph.mvp.base.model.BaseResponesModel;


public abstract class LogoutFilteErrorConsumer<T extends BaseResponesModel> extends LogoutConsumer<T> {

    public LogoutFilteErrorConsumer(Context context) {
        super(context, true);
    }
}
