package com.pbph.mvp.rxjava2.filterobserver;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.mvp.rxjava2.errorlistener.OnErrorToastShortListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * 注意内存泄漏：https://github.com/trello/RxLifecycle/tree/2.x
 * <p>
 */
public abstract class BaseFilter {

    private final static String THREAD_MAIN = "main";

    private Context mContext;

    private Map<Error, Class> filterMap = new HashMap<>();


    public BaseFilter() {
        this(null);
    }

    public BaseFilter(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public boolean errorfilter(Throwable t) {
        return filter(error -> !error.cls.getName().equals(t.getClass().getName()));
    }

    protected <T extends BaseResponesModel> boolean datafilter(T t) {
        return filter(error -> error.code != t.getCode());
    }


    public final boolean filter(@NonNull OnFilterListener filterListener) {

        Iterator iterator = filterMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Error, Class> entry = (Map.Entry<Error, Class>) iterator.next();

            Error key = entry.getKey();

            if (filterListener.onDo(key)) continue;

            Class value = entry.getValue();

            if (value == null) return false;

            if (!isMainThread()) return false;

            try {
                OnErrorListener errorListener = (OnErrorListener) Class.forName(value.getName()).newInstance();
                errorListener.onDo(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }


    public boolean isMainThread() {
        if (mContext == null) return false;
        return Thread.currentThread().getName().equals(THREAD_MAIN);
    }


    public void putErrorFilter(Class cls, String msg) {
        putErrorFilter(cls, msg, OnErrorToastShortListener.class);
    }

    public void putErrorFilter(Class cls, String msg, Class listener) {
        putFilterData(new Error(mContext, cls, msg), listener);
    }


    public void putDataFilter(int code, String msg) {
        putDataFilter(code, msg, OnErrorToastShortListener.class);
    }

    public void putDataFilter(int code, String msg, Class listener) {
        putFilterData(new Error(mContext, code, msg), listener);
    }

    public final void putFilterData(Error error, Class listener) {
        filterMap.put(error, listener);
    }

    public static class Error {

        public Context context;

        public Class cls;
        public int code;

        public String msg;

        public Error(Context context) {
            this(context, 0, null, null);
        }

        public Error(Context context, int code, String msg) {
            this(context, code, null, msg);
        }

        public Error(Context context, Class cls, String msg) {
            this(context, 0, cls, msg);
        }

        public Error(Context context, int code, Class cls, String msg) {

            this.context = context;

            this.code = code;
            this.cls = cls;

            this.msg = msg;
        }
    }

    public interface OnErrorListener {
        void onDo(Error error);
    }

    private interface OnFilterListener {
        boolean onDo(Error error);
    }

}
