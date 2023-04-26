package com.pbph.shoppingmall.module.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.account.login.LoginActivity;
import com.pbph.shoppingmall.module.main.MainActivity;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;

public class WelcomeActivity extends BaseActivity<Presenter> implements Contract.View {

    ImageView imageView;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_welcome;
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.imageView1);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        super.onPermissionsDenied(requestCode, perms);
        //权限被拒绝则无法进入程序
        finish();
    }


    @Override
    @AfterPermissionGranted(Presenter.REQUEST_CODE_4_STARTMAINACTIVITY)
    public void onGetPerms() {
        presenter.login();
    }

    @Override
    public void goLogin() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
//        startActivity(intent);
//        finish();
    }

    @Override
    public void goMain() {
        Intent intent = new Intent(getContext(), MainActivity.class);
//        startActivity(intent);
//        finish();

    }

}
