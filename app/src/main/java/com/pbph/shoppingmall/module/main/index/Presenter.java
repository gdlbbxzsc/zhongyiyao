package com.pbph.shoppingmall.module.main.index;


import android.Manifest;

import com.android.utils.PermissionUtils;
import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.model.message.Jump2ShopTypeMsg;
import com.pbph.shoppingmall.model.request.GetAppDefaultTemplateRequest;
import com.pbph.shoppingmall.model.response.GetAppDefaultTemplateResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

import pub.devrel.easypermissions.EasyPermissions;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }


    //即使没有用到后置函数，也要写一个这个常量，因为获取权限需要一个标识符。他可不仅仅是为了后置函数而存在的
    public static final int REQUEST_CODE_4_GOTOSCAN = 1111;

    //这个 就是 我比较喜欢的，仅仅是 检查权限，检查完了 要做什么你自己决定。
    //true 拥有权限，otherwise 没有权限，并正在请求中
    @Override
    public void checkPermission2gotoScan() {
        String[] permission4DoSomeThing = new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.VIBRATE};

//        这里检查没有获取到的权限列表
//        permission4DoSomeThing = PermissionUtils.checkNotAllowPermissions(getBaseView().getContext(), permission4DoSomeThing);

        boolean b = EasyPermissions.hasPermissions(getBaseView().getContext(), permission4DoSomeThing);
        if (b) {
            getBaseView().gotoScan();//这里我们要自己调用达到后置函数自动调用的目的
            return;
        }
//        //已经拥有权限，直接做什么事情，没有，则获取
//        if (permission4DoSomeThing == null || permission4DoSomeThing.length <= 0) {
//            getBaseView().gotoScan();//这里我们要自己调用达到后置函数自动调用的目的
//        } else {
        PermissionUtils.requestByEasyPermissions(getBaseView().getFragment(), REQUEST_CODE_4_GOTOSCAN, permission4DoSomeThing);
//        }
    }

    GetAppDefaultTemplateRequest<GetAppDefaultTemplateResponse> request = new GetAppDefaultTemplateRequest<>();

    @Override
    public void getHttpDatas() {

        request.request().subscribe(
                new LogoutConsumer<GetAppDefaultTemplateResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetAppDefaultTemplateResponse vo) throws Exception {
                        getBaseView().finishSmartRefresh();

                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }
                        getBaseView().setHttpDatas(vo.getData());
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().finishSmartRefresh();
                        getBaseView().toastShort("获取失败");
                    }
                }
        );
    }

    Jump2ShopTypeMsg msg = new Jump2ShopTypeMsg();

    @Override
    public void postRxBus4goShopType() {
        RxBusF.post0(msg);
    }

}
