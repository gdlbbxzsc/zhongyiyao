package com.pbph.shoppingmall.module.shoppingcar;

import android.widget.FrameLayout;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.shoppingcar.fragment.ShoppingCarFragment;

/**
 * Created by Administrator on 2018/1/19.
 * 购物车
 * 孟庆奎
 */

public class ShoppingCarActivity extends BaseActivity<Presenter> implements Contract.View {

    FrameLayout fl_container;

    ShoppingCarFragment fragment;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);

    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_shoppingcar;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        fl_container = findViewById(R.id.fl_container);

        fragment = ShoppingCarFragment.newInstance(true);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragment).commit();
    }
 
}
