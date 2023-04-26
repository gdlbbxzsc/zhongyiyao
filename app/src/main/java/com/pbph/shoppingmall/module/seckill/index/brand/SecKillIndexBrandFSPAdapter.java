package com.pbph.shoppingmall.module.seckill.index.brand;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pbph.shoppingmall.module.seckill.index.brand.fragment.SecKillIndexBrandItemCardFragment;

import java.util.List;

/**
 * Created by longhope on 2017/4/26.
 */

public class SecKillIndexBrandFSPAdapter extends FragmentStatePagerAdapter {

    public List<String> list;

    public SecKillIndexBrandFSPAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public SecKillIndexBrandItemCardFragment getItem(int position) {

        return SecKillIndexBrandItemCardFragment.newInstance(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
