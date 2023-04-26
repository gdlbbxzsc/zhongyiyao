package com.pbph.mvp.rxjava2.filterobserver;

import android.content.Context;
import android.widget.Toast;

import com.pbph.mvp.R;
import com.pbph.mvp.rxjava2.errorlistener.OnErrorToastShortListener;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;
import retrofit2.HttpException;


public abstract class BaseErrorConsumer<T extends Throwable> extends BaseFilter implements Consumer<T> {

    public BaseErrorConsumer(Context context) {
        super(context);

        putErrorFilter(HttpException.class, "网络错误");
        putErrorFilter(IOException.class, "没有网络，请检查网络连接");
        putErrorFilter(SocketTimeoutException.class, "服务器响应超时");
        putErrorFilter(ConnectException.class, "网络连接异常，请检查网络");
        putErrorFilter(UnknownHostException.class, "无法解析主机，请检查网络连接", OnErrorToastShortListener.class);

    }

    @Override
    public void accept(T throwable) throws Exception {

        boolean b = errorfilter(throwable);
        if (!b) return;
        throwable.printStackTrace();
        onDo(throwable);
    }

    public abstract void onDo(T throwable) throws Exception;
}
