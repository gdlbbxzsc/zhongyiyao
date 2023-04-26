package com.pbph.mvp.http.retrofit2;

import com.android.utils.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static final String baseUrl = "";

    private static final int connectTimeout = 4;
    private static final int readTimeout = 4;
    private static final int writeTimeout = 4;

//    public static TestMvpService createService() {
//        return createService(TestMvpService.class);
//    }

    public static <T> T createService(Class<T> cls) {
        return RetrofitInstance.INSTANCE.create(cls);
    }

    // 构建全局Retrofit客户端
    private static final class RetrofitInstance {

        private static final Retrofit INSTANCE = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OKHttpInstance.INSTANCE)
                //如果网络访问返回的字符串，而不是json数据格式，可以使用ScalarsConverterFactory转换器
//                .addConverterFactory(ScalarsConverterFactory.create())
                //支持对象转json串
                .addConverterFactory(GsonConverterFactory.create())
                //为了支持rxjava,需要添加下面这个把 Retrofit 转成RxJava可用的适配类
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    //构建全局okhttp客户端
    private static final class OKHttpInstance {

        //新建一个文件用来缓存网络请求
//        private static File cacheDirectory = new File(AppContext.getApplicationContext()
//                .getCacheDir().getAbsolutePath(), "HttpCache");
        private static final OkHttpClient INSTANCE = new OkHttpClient.Builder()
                //设置okhttp拦截器，这样做的好处是可以为你的每一个
                //retrofit2的网络请求都增加相同的head头信息，而不用每一个请求都写头信息
//                .addInterceptor((chain) -> {
//            //这两种写法 有一个差别 就是  关于 newBuilder的 如果实际应用的时候 查找一下。
////                    Request request = chain.request();
////                    okhttp3.Response proceed = chain.proceed(request);
////                    return proceed;
//
//
////                    Request.Builder builder = chain.request().newBuilder();
////                    builder.addHeader("token", "123");
////                    return chain.proceed(builder.build());
//                })

//消息拦截器
                .addInterceptor(new HttpLoggingInterceptor(message -> Logger.e(message)).setLevel(HttpLoggingInterceptor.Level.BODY))
                //设置连接超时
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                //设置从主机读信息超时
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                //设置写信息超时
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                //设置缓存文件
//                .cache(new Cache(cacheDirectory, 10 * 1024 * 1024))
                .build();
    }

}

