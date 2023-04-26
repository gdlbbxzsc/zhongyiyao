package com.pbph.mvp.rxjava2.filterobserver;

import android.content.Context;

import com.pbph.mvp.base.model.BaseResponesModel;


public abstract class FilteErrorConsumer<T extends BaseResponesModel> extends BaseConsumer<T> {


    public FilteErrorConsumer(Context context) {
        super(context, true);
    }
}
