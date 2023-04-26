package com.pbph.shoppingmall.module.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.utils.Logger;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.main.index.IndexFragment;
import com.pbph.shoppingmall.module.main.mine.MineFragment;
import com.pbph.shoppingmall.module.main.type.TypeFragment;
import com.pbph.shoppingmall.module.shoppingcar.fragment.ShoppingCarFragment;
import com.pbph.shoppingmall.utils.SpHelper;
import com.utils.DateUtils;
import com.utils.StringUtils;

public class MainActivity extends BaseActivity<Presenter> implements Contract.View {

    private FragmentTabHost mTabHost;

    private final Class[] tabFragmentArray = {IndexFragment.class, TypeFragment.class, ShoppingCarFragment.class, MineFragment.class};

    private final int[] tabContentArray = {R.layout.tabcontent_item_main1, R.layout.tabcontent_item_main2, R.layout.tabcontent_item_main4, R.layout.tabcontent_item_main3};

//    private Badge badgeView;

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected void initData() {
//        inflater = LayoutInflater.from(getContext());

        MyApplication.userInfo.setCustomerId(SpHelper.getInstance().getUserId());
        MyApplication.userInfo.setToken(SpHelper.getInstance().getToken());
    }

    @Override
    protected void initView() {

        //加载FragmentTabHost
        initTabHost();

        //设置当前默认界面
        mTabHost.setCurrentTab(0);
    }

    private void initTabHost() {

        mTabHost = findViewById(android.R.id.tabhost);

        mTabHost.setup(getContext(), getSupportFragmentManager(), R.id.container);


        LayoutInflater inflater = LayoutInflater.from(getContext());

        //getSupportFragmentManager().beginTransaction().
        for (int i = 0; i < tabFragmentArray.length; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            View tabView = inflater.inflate(tabContentArray[i], null);
            if (i == 2) {
                TextView tabShoppingcar = tabView.findViewById(R.id.tab_shoppingcar);
//                badgeView = new QBadgeView(getActivity()).bindTarget(tabShoppingcar).setBadgeNumber(0);
//                badgeView.setBadgeTextSize(8, true);
                /*badgeView.setOnDragStateChangedListener((dragState, badge, targetView)->{
                });*/
            }
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec("MainActivity_TabSpec_" + i).setIndicator(tabView);
            //将Tab按钮添加进Tab选项卡中
            Bundle bundle = new Bundle();
//            bundle.putString("url", "http://www.baidu.com");
            mTabHost.addTab(tabSpec, tabFragmentArray[i], bundle);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.getTabWidget().setBackgroundColor(Color.WHITE);

        mTabHost.setOnTabChangedListener(tabId -> {

            if (StringUtils.isEqual("MainActivity_TabSpec_2", tabId)) {
//                badgeView.hide(false);
                return;
            }

            if (MyApplication.userInfo.getCustomerId() == null || StringUtils.isEmpty(MyApplication.userInfo.getToken()))
                return;

            presenter.getShoppingCartNumber();
        });
    }

    @Override
    public void goShopType() {
        mTabHost.setCurrentTab(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MyApplication.userInfo.getCustomerId() == null || StringUtils.isEmpty(MyApplication.userInfo.getToken()))
            return;
        presenter.getShoppingCartNumber();
    }

    @Override
    public void setShoppingCartNumber(int num) {
//        if (badgeView == null) {
//            return;
//        }
//        if (num == 0) {
//            badgeView.hide(false);
//            return;
//        }
//        badgeView.setBadgeText(String.valueOf(num));
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
