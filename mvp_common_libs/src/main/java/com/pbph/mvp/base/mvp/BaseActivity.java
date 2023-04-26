package com.pbph.mvp.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.pbph.mvp.custom.dialog.WaitUI;
import com.utils.StringUtils;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    public BaseActivity activity;
    protected Context context;

    public T presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        context = this;

        initSysSetting();

        initData();

        setContentView(layoutResID());

        initView();
        bindPresenter();
        presenter.subscribe();
    }


    @Override
    protected void onPause() {
        hideSoftInput();
        super.onPause();
    }

    private void hideSoftInput() {
        View view = getCurrentFocus();
        if (view == null) return;

        IBinder binder = view.getWindowToken();
        if (binder == null) return;


//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (imm.isActive()) imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(binder, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void initSysSetting() {
        // 设置不能横屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }

    public void bindPresenter() {

        T presenter = createPresenter();

        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    public void toastShort(int content) {
        toast(content, Toast.LENGTH_SHORT);
    }

    public void toastLong(int content) {
        toast(content, Toast.LENGTH_LONG);
    }

    public void toastShort(String content) {
        toast(content, Toast.LENGTH_SHORT);
    }

    public void toastLong(String content) {
        toast(content, Toast.LENGTH_LONG);
    }

    private void toast(String content, int time) {

        if (TextUtils.isEmpty(content)) return;

        Toast.makeText(this, content, time).show();
    }

    private void toast(int content, int time) {
        Toast.makeText(this, content, time).show();
    }

    public void showProgressDialog(Object... objs) {
        WaitUI.Show(this);
    }

    public void hideLoadingDialog(Object... objs) {
        WaitUI.Cancel();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unsubscribe();
        }

    }

    public Context getContext() {
        return context;
    }

    public BaseActivity getActivity() {
        return activity;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        String[] strs = perms.toArray(new String[perms.size()]);
//        String str = PermissionUtils.checkAllowPermissions2String(getContext(), strs);
        String str = StringUtils.builderJoin("\n", strs);
        toastShort("已获取:\n" + str + "\n权限");
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        String[] strs = perms.toArray(new String[perms.size()]);
//        String str = PermissionUtils.checkNotAllowPermissions2String(getContext(), strs);
        String str = StringUtils.builderJoin("\n", strs);
        toastShort("已拒绝:\n" + str + "\n权限");


        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {

            toastShort("已拒绝:\n" + str + "\n权限,并不再询问");

            new AppSettingsDialog
                    .Builder(this)
                    .setRationale("此功能需要" + str + "权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("好")
                    .setNegativeButton("不行")
                    .build()
                    .show();
        }
    }

    protected abstract int layoutResID();

    protected abstract T createPresenter();

    protected abstract void initData();

    protected abstract void initView();
}
