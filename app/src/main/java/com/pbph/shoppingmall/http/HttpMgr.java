package com.pbph.shoppingmall.http;

import com.pbph.mvp.http.retrofit2.BaseHttpClient;
import com.pbph.shoppingmall.constant.Constant;


public class HttpMgr extends BaseHttpClient {

    @Override
    public String getBaseUrl() {
        return Constant.URL.BASE_URL;
    }

    public static HttpService createService() {
        return getInstance().createService(HttpService.class);
    }

    public static HttpMgr getInstance() {
        return InnerInstance.INSTANCE;
    }

    private HttpMgr() {
    }

    // 构建全局Retrofit客户端
    private static final class InnerInstance {
        private static final HttpMgr INSTANCE = new HttpMgr();
    }

}
