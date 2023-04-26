package com.pbph.shoppingmall.utils.custom;

import android.view.View;

/**
 * Created by Administrator on 2017/9/18.
 */


public abstract class OnSPClickListener extends BaseSPListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        if (!canClick()) return;
        onClickSucc(v);
    }

    public abstract void onClickSucc(View v);

}
