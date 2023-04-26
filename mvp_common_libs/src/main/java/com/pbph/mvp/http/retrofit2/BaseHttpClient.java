package com.pbph.mvp.http.retrofit2;

import com.android.utils.Logger;
import com.pbph.mvp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseHttpClient {

    private String baseUrl = "";

    private int connectTimeout = 8;
    private int readTimeout = 8;
    private int writeTimeout = 8;

    Retrofit retrofit;

    public BaseHttpClient() {

        baseUrl = getBaseUrl();

        connectTimeout = getConnectTimeout();
        readTimeout = getReadTimeout();
        writeTimeout = getWriteTimeout();


        OkHttpClient okHttpClient = initOkHttpClient();

        retrofit = initRetrofit(okHttpClient);

    }


    private OkHttpClient initOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new HttpLoggingInterceptor(message -> {
            if (BuildConfig.LOG) Logger.e(message);
        }).setLevel(HttpLoggingInterceptor.Level.BODY))
                //设置连接超时
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                //设置从主机读信息超时
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                //设置写信息超时
                .writeTimeout(writeTimeout, TimeUnit.SECONDS);


        builder = initOkHttpBuilder(builder);


        return builder.build();
    }

    private Retrofit initRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl)
                .client(okHttpClient)
                //如果网络访问返回的字符串，而不是json数据格式，可以使用ScalarsConverterFactory转换器
//                .addConverterFactory(ScalarsConverterFactory.create())
                //支持对象转json串
                .addConverterFactory(GsonConverterFactory.create())
                //为了支持rxjava,需要添加下面这个把 Retrofit 转成RxJava可用的适配类
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        builder = initREtrofitBuilder(builder);

        return builder.build();
    }

    public <T> T createService(Class<T> cls) {
        return retrofit.create(cls);
    }

    public abstract String getBaseUrl();

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public Retrofit.Builder initREtrofitBuilder(Retrofit.Builder builder) {
        return builder;
    }

    public OkHttpClient.Builder initOkHttpBuilder(OkHttpClient.Builder builder) {
        return builder;
    }
}

