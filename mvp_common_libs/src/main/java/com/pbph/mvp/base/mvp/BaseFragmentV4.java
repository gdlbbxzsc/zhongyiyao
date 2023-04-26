package com.pbph.mvp.base.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.utils.Logger;
import com.pbph.mvp.custom.dialog.WaitUI;
import com.utils.StringUtils;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


public abstract class BaseFragmentV4<T extends IBasePresenter> extends Fragment implements EasyPermissions.PermissionCallbacks {

    public BaseFragmentV4 fragment;
    public Context context;

    public T presenter;

    public View mContentView = null;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.e(this + " setUserVisibleHint " + isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.context == null) {
            this.context = context;
            this.fragment = this;
        }
        Logger.e(this + " onAttach new");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (this.context == null) {
            this.context = getActivity();
            this.fragment = this;
        }
        Logger.e(this + " onAttach old");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e(this + " onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(layoutResID(), container, false);
            initView(mContentView);
            bindPresenter();

            Logger.e(this + " onCreateView new");
        }
        presenter.subscribe();
        Logger.e(this + " onCreateView old");
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e(this + " onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.e(this + " onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.e(this + " onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.e(this + " onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.e(this + " onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.e(this + " onDestroyView");
        //解绑 presenter
        if (presenter != null) {
            presenter.unsubscribe();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e(this + " onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.e(this + " onDetach");
    }

    public Context getContext() {
        return context;
    }

    public BaseFragmentV4 getFragment() {
        return fragment;
    }

    public void bindPresenter() {

        T presenter = createPresenter();

        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    public void toastShort(String content) {
        toast(content, Toast.LENGTH_SHORT);
    }

    public void toastLong(String content) {
        toast(content, Toast.LENGTH_LONG);
    }

    public void toastShort(int content) {
        toast(content, Toast.LENGTH_SHORT);
    }

    public void toastLong(int content) {
        toast(content, Toast.LENGTH_LONG);
    }

    private void toast(String content, int time) {


        if (TextUtils.isEmpty(content)) return;

        Toast.makeText(context, content, time).show();
    }

    private void toast(int content, int time) {

        Toast.makeText(context, content, time).show();
    }

    public void showProgressDialog(Object... objs) {
        WaitUI.Show(context);
    }

    public void hideLoadingDialog(Object... objs) {
        WaitUI.Cancel();
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

    public abstract void initView(View view);
}
