package com.pbph.mvp.base.mvp;

import android.content.Context;


public interface IBaseView {

    Context getContext();

    void toastShort(String content);

    void toastLong(String content);

    void toastShort(int content);

    void toastLong(int content);

    void showProgressDialog(Object... objs);

    void hideLoadingDialog(Object... objs);


}
