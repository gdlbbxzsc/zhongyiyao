package com.pbph.mvp.base;

import android.app.Application;

import com.android.utils.FileUtils;
import com.android.utils.SystemUtils;

import java.util.HashMap;
import java.util.Map;

public class BaseApplication extends Application {

    //用于界面间数据透传--by gdl key the Class.getname value the data you want
    private static Map<String, Object> dataMap = new HashMap();

    @Override
    public void onCreate() {
        super.onCreate();

        if (!getApplicationInfo().packageName.equals(SystemUtils.getCurProcessName(getApplicationContext())))
            return;

        FileUtils.init(getPackageName());
    }


    /////////////////////
    public static Object getDataMapData(String key) {
        Object obj = dataMap.get(key);
        dataMap.remove(key);
        return obj;
    }

    public static void setDataMapData(String key, Object val) {
        dataMap.put(key, val);
    }
///////////////////////

}
