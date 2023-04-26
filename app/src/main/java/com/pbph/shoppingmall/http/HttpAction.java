package com.pbph.shoppingmall.http;


import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.mvp.rxjava2.Rx2Helper;
import com.pbph.shoppingmall.model.request.BaseRequest;

import java.lang.reflect.Method;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/12/4.
 */

public class HttpAction<T extends BaseResponesModel> {
    public static HttpAction getInstance() {
        return InnerInstance.INSTANCE;
    }


    private static class InnerInstance {
        private static HttpAction INSTANCE = new HttpAction();
    }

    HttpService service = HttpMgr.createService();

    private HttpAction() {
    }

    public Observable<T> request(String methodName, BaseRequest request) {
        try {
            Method method = service.getClass().getMethod(methodName, Map.class);
            Object obj = method.invoke(service, request.toMap());

            return Rx2Helper.applySchedulers((Observable<T>) obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
