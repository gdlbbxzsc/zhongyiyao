package com.pbph.shoppingmall.utils;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.android.utils.Logger;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.mvp.rxjava2.filterobserver.FilteErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.request.GetOssTokenRequest;
import com.pbph.shoppingmall.model.response.GetOssTokenResponse;

import java.util.List;


public class AliOss {

    private Context context = null;

    //
    private OSSClient oss;

    //
    private String ACCESS_KEY_ID;
    private String ACCESS_KEY_SECRET;
    private String SECURITY_TOKEN;

    //
    private final String BACKET_NAME = "pbkjb2b2cbucket";
    private final String ENDPOINT = "oss-cn-beijing.aliyuncs.com";
    // 阿里云API的文件夹名称
    private final String FOLDER = "appImg/";
    private final String FIX = ".jpg";


    public static AliOss getInstance() {
        return Inner.aliOss;
    }

    private static class Inner {
        private static final AliOss aliOss = new AliOss();
    }

    private AliOss() {
    }

    private void initOOS() {
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(ACCESS_KEY_ID, ACCESS_KEY_SECRET, SECURITY_TOKEN);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
//        OSSLog.enableLog(); //这个开启会支持写入手机sd卡中的一份日志文件位置在SD_path\OSSLog\logs.csv
        OSSLog.disableLog();
        oss = new OSSClient(context, ENDPOINT, credentialProvider, conf);
    }


    private void getOssToken2uplodImage(Context context, String path, OnOosUploadListener onListener) {

        this.context = context;

        GetOssTokenRequest<GetOssTokenResponse> request = new GetOssTokenRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new FilteErrorConsumer<GetOssTokenResponse>(context) {
                    @Override
                    public void onDo(GetOssTokenResponse vo) throws Exception {
                        GetOssTokenResponse.DataBean dataBean = vo.getData();

                        ACCESS_KEY_ID = dataBean.getAccessKeyId();
                        ACCESS_KEY_SECRET = dataBean.getAccessKeySecret();
                        SECURITY_TOKEN = dataBean.getSecurityToken();

                        initOOS();
                        uplodImage(context, path, onListener);
                    }
                }
                , new BaseErrorConsumer<Throwable>(context) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        onListener.onFail();
                    }
                }
        );
    }

    public void uplodImageList(Context context, List<String> list, OnOosUploadListener onListener) {
        for (String str : list) {
            uplodImage(context, str, onListener);
        }
    }

    public void uplodImage(Context context, String path, OnOosUploadListener onListener) {
        if (oss == null) {
            getOssToken2uplodImage(context, path, onListener);
            return;
        }
        final String pre = getUploadImagePath();//地址前半部分
        final String name = getUploadImageName(pre, path);//地址后半部分


        PutObjectRequest put = new PutObjectRequest(BACKET_NAME, name, path);
//        // 异步上传时可以设置进度回调
        put.setProgressCallback((request, currentSize, totalSize) -> {
            Logger.e(totalSize + " " + currentSize, "上传进度：");
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult putResult) {

                onListener.onSuccess(pre + name);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    serviceException.printStackTrace();
                }
                onListener.onFail();
            }
        });
    }

    private String getUploadImagePath() {
        return "http://" + BACKET_NAME + "." + ENDPOINT + "/";
    }

    private String getUploadImageName(String pre, String localPath) {
        try {
            byte[] bytes = new StringBuilder().append(pre).append(System.currentTimeMillis()).append(localPath).toString().getBytes();
            return FOLDER + MD5Utils.encrypt(bytes) + FIX;
        } catch (Exception e) {
            e.printStackTrace();
            return System.currentTimeMillis() + FIX;
        }
    }

    public interface OnOosUploadListener {

        void onSuccess(String path);

        void onFail();
    }

}
