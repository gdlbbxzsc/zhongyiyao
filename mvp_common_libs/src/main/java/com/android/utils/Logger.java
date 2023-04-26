package com.android.utils;

import android.util.Log;

import com.pbph.mvp.BuildConfig;


public final class Logger {

    private static final String DEFAULT_TAG = "===>";

    public static void e(Object obj, String... strs) {
        if (BuildConfig.LOG) Log.e(getTag(strs), obj.toString());
    }

    public static void d(Object obj, String... strs) {
        if (BuildConfig.LOG) Log.d(getTag(strs), obj.toString());
    }

    public static void i(Object obj, String... strs) {
        if (BuildConfig.LOG) Log.i(getTag(strs), obj.toString());
    }

    public static void w(Object obj, String... strs) {
        if (BuildConfig.LOG) Log.w(getTag(strs), obj.toString());
    }

    private static String getTag(String... strs) {
        if (strs == null || strs.length <= 0) return DEFAULT_TAG;
        if (strs[0] == null || strs[0].length() <= 0) return DEFAULT_TAG;
        return strs[0];
    }
}
