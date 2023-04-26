package com.pbph.shoppingmall.module.goodsdetail.goodsdetailsdetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/5/24.
 */

public class GoodsDetailsDetailAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
//    private String[] titles = {"商品介绍", "规格参数", "包装售后"};
    private String[] titles = {"商品介绍", "包装售后"};
    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public GoodsDetailsDetailAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null && fragments.size() > 0 ? fragments.size() : 0;
    }

    //重写这个方法，将设置每个Tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
