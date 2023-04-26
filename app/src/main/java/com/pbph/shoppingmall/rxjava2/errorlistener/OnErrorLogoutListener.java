package com.pbph.shoppingmall.rxjava2.errorlistener;

import android.content.Intent;

import com.pbph.mvp.rxjava2.filterobserver.BaseFilter;
import com.pbph.shoppingmall.module.account.login.LoginActivity;

/**
 * Created by Administrator on 2018/1/9.
 * 监听登录互斥错误码的listener
 */

public class OnErrorLogoutListener implements BaseFilter.OnErrorListener {

    @Override
    public void onDo(BaseFilter.Error error) {
        //TODO 这里我应该做一个跳转到登录页面或者是登录弹窗。
        error.context.startActivity(new Intent(error.context, LoginActivity.class));
    }
}