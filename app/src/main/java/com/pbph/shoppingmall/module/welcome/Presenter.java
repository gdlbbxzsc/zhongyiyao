package com.pbph.shoppingmall.module.welcome;


import android.Manifest;
import android.os.Handler;

import com.android.utils.PermissionUtils;
import com.pbph.mvp.base.mvp.BasePresenter;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    private long lastTime = System.currentTimeMillis();

    private static final long DURATION_TIMES = 2500;


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        checkPermission2onGetPerms();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }

    //获取程序所需权限
    public static final int REQUEST_CODE_4_STARTMAINACTIVITY = 0;

    @Override
    public void checkPermission2onGetPerms() {
//        Rx2Helper.applySchedulers(Observable.create((ObservableOnSubscribe<String[]>) e -> {
//            String[] perms = new String[]{//
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE
////                    , Manifest.permission.READ_PHONE_STATE
////                    , Manifest.permission.ACCESS_COARSE_LOCATION
////                    , Manifest.permission.ACCESS_FINE_LOCATION
//                    //
//            };
//            perms = PermissionUtils.checkNotAllowPermissions(getBaseView().getContext(), perms);
//            if (perms == null) perms = new String[]{};
//            e.onNext(perms);
//        })).subscribe(strings -> {
//            if (strings == null || strings.length <= 0) {
//                login();
//            } else {
//                PermissionUtils.requestByEasyPermissions(getBaseView().getActivity(), REQUEST_CODE_4_STARTMAINACTIVITY, strings);
//            }
//        });

        String[] perms = new String[]{//
                Manifest.permission.WRITE_EXTERNAL_STORAGE
//                , Manifest.permission.READ_PHONE_STATE
//                , Manifest.permission.ACCESS_COARSE_LOCATION
//                , Manifest.permission.ACCESS_FINE_LOCATION
                //
        };

        perms = PermissionUtils.checkNotAllowPermissions(getBaseView().getContext(), perms);
        if (perms == null || perms.length <= 0) {
            login();
        } else {
            PermissionUtils.requestByEasyPermissions(getBaseView().getActivity(), REQUEST_CODE_4_STARTMAINACTIVITY, perms);
        }
    }


    @Override
    public void login() {
        //TODO 自动登录的网络请求

        long nowTime = scaleTimes();

        onSucc(nowTime);
//        onFail(nowTime);
    }

    private long scaleTimes() {
        long nowTime = DURATION_TIMES - (System.currentTimeMillis() - lastTime);
        return nowTime < 0 ? 0 : nowTime;
    }

    private void onSucc(long nowTime) {
        new Handler().postDelayed(() -> getBaseView().goMain(), nowTime);
    }

    private void onFail(long nowTime) {
        new Handler().postDelayed(() -> getBaseView().goLogin(), nowTime);
    }
}
