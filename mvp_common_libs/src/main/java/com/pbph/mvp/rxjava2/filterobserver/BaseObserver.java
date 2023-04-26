package com.pbph.mvp.rxjava2.filterobserver;

import android.content.Context;

import com.pbph.mvp.rxjava2.errorlistener.OnErrorToastShortListener;
import com.pbph.mvp.base.model.BaseResponesModel;

import java.net.UnknownHostException;

import io.reactivex.Observer;


public abstract class BaseObserver<T extends BaseResponesModel> extends BaseFilter implements Observer<T> {

    public BaseObserver(Context context) {
        super(context);

        putDataFilter(0, "数据错误", OnErrorToastShortListener.class);

        putErrorFilter(UnknownHostException.class, "无法解析主机，请检查网络连接");
    }

    @Override
    public void onNext(T t) {

        boolean b = datafilter(t);
        if (!b) return;

        onNextDo(t);
    }

    @Override
    public void onError(Throwable e) {

        boolean b = errorfilter(e);
        if (!b) return;

        onErrorDo(e);
    }

    public abstract void onNextDo(T vo);

    public abstract void onErrorDo(Throwable e);

}
